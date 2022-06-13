package pl.tasksForWorkers.RestApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tasksForWorkers.RestApi.controller.TaskController;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.repository.TaskRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    final private Logger logger = LoggerFactory.getLogger(TaskController.class);

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

    public int update(int idOfOldTask, Task updatedTask){
        return patchOldTaskWithNewTask(idOfOldTask, updatedTask, false);
    }

    public int patchUpdate(int id, Task updatedTask){
        return patchOldTaskWithNewTask(id, updatedTask, true);
    }

    public int delete(int id){
        return taskRepository.delete(id);
    }

    private int patchOldTaskWithNewTask(int idOfOldTask, Task updatedTask, boolean isPatchUpdate) {
        Task oldTask = taskRepository.getById(idOfOldTask);

        if (isPatchUpdate) {
            if (updatedTask.getTitle() != null)
                oldTask.setTitle(updatedTask.getTitle());
            if (updatedTask.getDescription() != null)
                oldTask.setDescription(updatedTask.getDescription());
            if (updatedTask.getState() != null)
                oldTask.setState(updatedTask.getState());
            if (updatedTask.getDeathline() != null)
                oldTask.setDeathline(updatedTask.getDeathline());
            if (updatedTask.getWorkersList() != null)
                oldTask.setWorkersList(updatedTask.getWorkersList());
        }
        else
            oldTask = updatedTask;

        return taskRepository.update(idOfOldTask, oldTask);
    }
}
