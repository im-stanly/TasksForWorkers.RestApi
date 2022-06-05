package pl.tasksForWorkers.RestApi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tasksForWorkers.RestApi.exception.TaskNotFoundException;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.util.List;

@Repository
public class TaskRepository {

    private final String GET_TASK_PROPERTIES_SQL = "SELECT id, title, state, description, deathline FROM task";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WorkerRepository workerRepository;

    public List<Task> getAllTasks(){
        List<Task> tasks = jdbcTemplate.query(GET_TASK_PROPERTIES_SQL,
                BeanPropertyRowMapper.newInstance(Task.class));
        for (Task task: tasks)
            task.setWorkersList(workerRepository.getWorkersByTaskId(task.getId()));
        return tasks;
    }

    public Task getById(int id){
        return getSingleTaskByObject("id", id);
    }

    public Task getByTitle(String title){
        return getSingleTaskByObject("title", title);
    }

    public int save(List<Task> tasks){
        tasks.forEach(task -> {
            jdbcTemplate
                    .update(
                    "INSERT INTO task(title, description, state, description, deathline) VALUES(?, ?, ?, ?, ?)",
                    task.getTitle(), task.getDescription(), task.getState(), task.getDeathline());

            saveWorkersForTask(task);
        });
        return 202;
    }

    public int update(Task task){
        int result = jdbcTemplate.update(
                "UPDATE task SET title=?, description=?, state=?, deathline=? WHERE id=?",
                task.getTitle(), task.getDescription(), task.getState(), task.getDeathline(), task.getId());
        saveWorkersForTask(task);

        return result;
    }

    public int delete(int id){
        if (!workerRepository.isElementOfLibrary("task", "id", id))
            throw new TaskNotFoundException(id);
        return jdbcTemplate.update("DELETE FROM task WHERE id = ?", id);
    }

    private Task getSingleTaskByObject(String kind, Object object){
        if (!workerRepository.isElementOfLibrary("task", kind, object)){
            if (object.getClass().equals(String.class))
                throw new TaskNotFoundException((String) object);
            else
                throw new TaskNotFoundException((Integer) object);
        }
        Task task = jdbcTemplate.queryForObject(GET_TASK_PROPERTIES_SQL + " WHERE "
                + kind + "=?", BeanPropertyRowMapper.newInstance(Task.class), object);

            task.setWorkersList(workerRepository.getWorkersByTaskId(task.getId()));

        return task;
    }

    private void saveWorkersForTask(Task taks){
        List<Worker> workers = taks.getWorkersList();

        int resultCode = 202;
        for (Worker worker: workers){
            resultCode = jdbcTemplate.update("UPDATE worker SET taskId = ? WHERE id = ?",
                    worker.getTaskId(), worker.getId());
        }
        return;
    }

}
