package pl.tasksForWorkers.RestApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.repository.TaskRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.getAllTasks();
    }

    public Task getById(int id){
        return taskRepository.getById(id);
    }

    public Task getByTitle(String title){
        return taskRepository.getByTitle(title);
    }

    public int saveTasks(List<Task> tasks){
        return taskRepository.save(tasks);
    }

    public int update(int id, Task updatedTask){
        return patchOldTaskWithNewTask(id, updatedTask, false);
    }

    public int patchUpdate(int id, Task updatedTask){
        return patchOldTaskWithNewTask(id, updatedTask, true);
    }

    public int delete(int id){
        return taskRepository.delete(id);
    }

    private int patchOldTaskWithNewTask(int id, Task updatedTask, boolean isPatchUpdate){
        Task oldTask = taskRepository.getById(id);
        if (oldTask != null){
            if (!isPatchUpdate || updatedTask.getTitle() != null)
                oldTask.setTitle(updatedTask.getTitle());
            if (!isPatchUpdate || updatedTask.getDescription() != null)
                oldTask.setDescription(updatedTask.getDescription());
            if (!isPatchUpdate || updatedTask.getState() != null)
                oldTask.setState(updatedTask.getState());
            if (!isPatchUpdate || updatedTask.getDeathline() != null)
                oldTask.setDeathline(updatedTask.getDeathline());
            if (!isPatchUpdate || updatedTask.getWorkersList() != null)
                oldTask.setWorkersList(updatedTask.getWorkersList());

            return taskRepository.update(oldTask);
        }
        else
            return 501;
    }
}
