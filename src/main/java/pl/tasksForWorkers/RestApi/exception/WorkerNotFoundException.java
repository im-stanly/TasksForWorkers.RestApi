package pl.tasksForWorkers.RestApi.exception;


public class WorkerNotFoundException extends RuntimeException{

    public WorkerNotFoundException(int id){
        super("Worker with id = " + id + " doesn't exist");
    }

    public WorkerNotFoundException(String name){
        super("Worker with name = " + name + " doesn't exist");
    }
}
