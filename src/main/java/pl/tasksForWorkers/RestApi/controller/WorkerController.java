package pl.tasksForWorkers.RestApi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.tasksForWorkers.RestApi.model.Worker;
import pl.tasksForWorkers.RestApi.service.WorkerService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("")
    public List<Worker> getWorkers(){
        log.info("User has entered the /workers endpoint");
        log.debug("entering getWorkers() in workerService...");

        List<Worker> worker = workerService.getWorkers();

        log.debug("exiting workerService. All went well");
        return worker;
    }

    @GetMapping("/id={id}")
    public Worker getById(@PathVariable("id") int id){
        log.info("User has entered the /workers/id=" + id + "  endpoint");
        log.debug("entering getById() in workerService, id = " + id);

        Worker worker = workerService.getById(id);

        log.debug("exiting workerService, worker = " + worker);
        return worker;
    }

    @GetMapping("/name={name}")
    public Worker getByTitle(@PathVariable("name") String name){
        log.info("User has entered the /workers/name=" + name + " endpoint");
        log.debug("entering getByName() in workerService, name = " + name);

        Worker worker = workerService.getByName(name);

        log.debug("exiting workerService, worker = " + worker);
        return worker;
    }

    @PostMapping("")
    public int add(@RequestBody List<Worker> worker){
        log.info("starting to save list of workers...");
        log.debug("entering save() in workerService, workers = " + worker);

        int response = workerService.save(worker);

        log.debug("response code is " + response);
        return response;
    }

    @PutMapping("/id={id}")
    public int update(@PathVariable("id") int id, @RequestBody Worker updatedWorker){
        log.info("starting to update the worker");
        log.debug("entering update() in workerService, id = " + id + ", worker = " + updatedWorker);

        int response = workerService.update(id, updatedWorker);

        log.debug("response code is " + response);
        return response;
    }

    @PatchMapping("/id={id}")
    public int patchUpdate(@PathVariable("id") int id, @RequestBody Worker updatedWorker){
        log.info("starting to patchUpdate the worker");
        log.debug("entering patchUpdate() in workerService, id = " + id + ", worker = " + updatedWorker);

        int response = workerService.patchUpdate(id, updatedWorker);

        log.debug("response code is " + response);
        return response;
    }

    @DeleteMapping("/id={id}")
    public int delete(@PathVariable("id") int id){
        log.info("starting to delete the worker");
        log.debug("entering delete() in workerService, id = " + id);

        int response = workerService.delete(id);

        log.debug("response code is " + response);
        return response;
    }
}
