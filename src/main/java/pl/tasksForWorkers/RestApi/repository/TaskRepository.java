package pl.tasksForWorkers.RestApi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.util.List;

@Repository
public class TaskRepository {

    private final String GET_TASK_PROPERTIES_SQL = "SELECT id, title, state, description, deathline FROM task";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Task> getAllTasks(){
        List<Task> tasks = jdbcTemplate.query(GET_TASK_PROPERTIES_SQL,
                BeanPropertyRowMapper.newInstance(Task.class));
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
                    .update("INSERT INTO task(title, description, state, description, deathline) VALUES(?, ?, ?, ?, ?)",
                    task.getTitle(), task.getDescription(), task.getState(), task.getDeathline());

            saveWorkersForTask(task);
        });
        return 202;
    }

    private Task getSingleTaskByObject(String kind, Object object){
        return jdbcTemplate.queryForObject(GET_TASK_PROPERTIES_SQL + " WHERE "
        + kind + "=?", BeanPropertyRowMapper.newInstance(Task.class), object);
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
