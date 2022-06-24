package pl.tasksForWorkers.RestApi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tasksForWorkers.RestApi.exception.TaskNotFoundException;
import pl.tasksForWorkers.RestApi.model.Task;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void getAllTasksShouldReturnListOfTasksOrEmptyList() {
        assertNotNull(taskRepository.getAllTasks());
    }

    @Test
    void getTaskByIdNegativeShouldNotBePossible() {
        int id = -1;

        assertThrows(TaskNotFoundException.class,
                () -> taskRepository.getById(id));
    }

    @Test
    void getTaskByIdFromDbShouldBePossible() {
        int id = 1;

        assertDoesNotThrow(
                () -> taskRepository.getById(id));
    }

    @Test
    void getByTitleWhenTitleIsNullShouldNotBePossible() {
        String title = "";

        assertThrows(TaskNotFoundException.class,
                () -> taskRepository.getByTitle(title));
    }

    @Test
    void saveListOfTasksShouldBePossible() {
        List<Worker> workers = new LinkedList<>();
        Worker newWorker = new Worker(3, 1, "Ben", "Johnson", "benny.com");
        workers.add(newWorker);

        Task newTask = new Task(3, "Project3", "description", "not done yet", LocalDate.now(), workers);
        List<Task> tasks = new LinkedList<>();
        tasks.add(newTask);

        int response = taskRepository.save(tasks);

        assertEquals(202, response);
    }

    @Test
    void updateReturnsNumbOfChangeRowsSoShouldBeZeroIfWeChangeNothing() {
        int idTaskToUpdate = 1;
        Task task = taskRepository.getById(idTaskToUpdate);

        assertEquals(1, taskRepository.update(idTaskToUpdate, task));
    }
}