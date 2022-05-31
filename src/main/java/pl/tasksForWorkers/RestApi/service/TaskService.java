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
        Task oldTask = taskRepository.getById(id);
        if (oldTask != null){
            oldTask.setTitle(updatedTask.getTitle());
            oldTask.setDescription(updatedTask.getDescription());
            oldTask.setState(updatedTask.getState());
            oldTask.setDeathline(updatedTask.getDeathline());
            oldTask.setWorkersList(updatedTask.getWorkersList());

            return taskRepository.update(oldTask);
        }
        return 501;

    }
}
