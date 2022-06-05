package pl.tasksForWorkers.RestApi.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(int id){
        super("Task with id = " + id + " doesn't exist");
    }

    public TaskNotFoundException(String string){
        super("Task with title '" + string + "' doesn't exist");
    }
}
