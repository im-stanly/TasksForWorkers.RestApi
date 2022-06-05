package pl.tasksForWorkers.RestApi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController{
    @GetMapping()
    public String hello(){
        return "Hello my friend, have a nice day! ";
    }
}
