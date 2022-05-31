package pl.tasksForWorkers.RestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/id={id}")
    public Task getById(@PathVariable("id") int id){
        return taskService.getById(id);
    }

    @GetMapping("/title={title}")
    public Task getByTitle(@PathVariable("title") String title){
        return taskService.getByTitle(title);
    }

    @PostMapping("")
    public int add(@RequestBody List<Task> tasks){
        return taskService.saveTasks(tasks);
    }

}
