package pl.tasksForWorkers.RestApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("")
    public int add(@RequestBody List<Worker> worker){
        return workerService.save(worker);
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable("id") int id, @RequestBody Worker updatedWorker){
        return workerService.update(id, updatedWorker);
    }

    @PatchMapping("/id={id}")
    public int patchUpdate(@PathVariable("id") int id, @RequestBody Worker updatedWorker){
        return workerService.patchUpdate(id, updatedWorker);
    }

    @DeleteMapping("id={id}")
    public int delete(@PathVariable("id") int id){
        return workerService.delete(id);
    }
}
