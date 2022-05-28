package pl.tasksForWorkers.RestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }
}
