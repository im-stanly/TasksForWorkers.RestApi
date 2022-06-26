package pl.tasksForWorkers.RestApi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.service.TaskService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public List<Task> getAllTasks(){
        log.info("User has entered the /tasks endpoint");
        log.debug("entering getAllTasks() in taskService...");

        List<Task> allTasks = taskService.getAllTasks();

        log.debug("exiting taskService. All went well");
        return allTasks;
    }

    @GetMapping("/id={id}")
    public Task getById(@PathVariable("id") int id){
        log.info("User has entered the /tasks/id=" + id + " endpoint");
        log.debug("entering getById() in taskService, id = " + id);

        Task task = taskService.getById(id);

        log.debug("exiting taskService, task = " + task);
        return task;
    }

    @GetMapping("/title={title}")
    public Task getByTitle(@PathVariable("title") String title){
        log.info("User has entered the /tasks/title=" + title + " endpoint");
        log.debug("entering getByTitle() in taskService, title = " + title);

        Task task = taskService.getByTitle(title);

        log.debug("exiting taskService, task = "  + task);
        return task;
    }

    @PostMapping("")
    public int add(@RequestBody List<Task> tasks){
        log.info("starting to save list of tasks...");
        log.debug("entering saveTasks() in taskService, tasks = " + tasks);

        int response = taskService.saveTasks(tasks);

        log.debug("response code is " + response);
        return response;
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable("id") int id, @RequestBody Task updatedTask){
        log.info("starting to update the task...");
        log.debug("entering update() in taskService, id = " + id + " , Task = " + updatedTask);

        int response = taskService.update(id, updatedTask);

        log.debug("response code is " + response);
        return response;
    }

    @PatchMapping("/id={id}")
    public int patchUpdate(@PathVariable("id") int id, @RequestBody Task updatedTask){
        log.info("starting to patchUpdate the task...");
        log.debug("entering patchUpdate() in taskService, id = " + id + " , Task = " + updatedTask);

        int response = taskService.patchUpdate(id, updatedTask);

        log.debug("response code is " + response);
        return response;
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable("id") int id){
        log.info("starting to delete the task...");
        log.debug("entering delete() in taskService, id = " + id);

        int response = taskService.delete(id);

        log.debug("response code is " + response);
        return response;
    }

}
