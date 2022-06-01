package pl.tasksForWorkers.RestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tasksForWorkers.RestApi.model.Worker;
import pl.tasksForWorkers.RestApi.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("")
    public List<Worker> getWorkers(){
        return workerService.getWorkers();
    }

    @GetMapping("id={id}")
    public Worker getById(@PathVariable("id") int id){
        return workerService.getById(id);
    }

    @GetMapping("title={title}")
    public Worker getByTitle(@PathVariable("title") String title){
        return workerService.getBytitle(title);
    }
}
