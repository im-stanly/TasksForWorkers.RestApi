package pl.tasksForWorkers.RestApi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.tasksForWorkers.RestApi.exception.TaskNotFoundException;
import pl.tasksForWorkers.RestApi.model.ExceptionModel;

import java.time.LocalDateTime;

@ControllerAdvice
public class TaskNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionModel taskNotFoundHandler(TaskNotFoundException ex){
        return new ExceptionModel(LocalDateTime.now(), 404, ex.getMessage());
    }
}
