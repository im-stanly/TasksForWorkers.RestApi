package pl.tasksForWorkers.RestApi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tasksForWorkers.RestApi.exception.WorkerNotFoundException;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkerRepositoryTest {
    @Autowired
    WorkerRepository workerRepository;

    @Test
    void getWorkersShouldReturnListOfWorkersOrEmptyList() {
        assertNotNull(workerRepository.getWorkers());
    }

    @Test
    void getWorkerByIdNegativeShouldNotBePossible() {
        int id = -1;

        assertThrows(WorkerNotFoundException.class,
                () -> workerRepository.getById(id));
    }

    @Test
    void getWorkerByIdOneShouldBePossible(){
        int id = 1;

        assertDoesNotThrow(() -> workerRepository.getById(id));
    }

    @Test
    void getWorkersByTaskIdOneShouldBePossible() {
        assertDoesNotThrow(() -> workerRepository.getWorkersByTaskId(1));
    }

    @Test
    void getWorkersByTaskIdFiveShouldNotBePossible() {
        assertThrows(WorkerNotFoundException.class,
                () -> workerRepository.getById(5));
    }

    @Test
    void saveListOfWorkerShouldBePossible() {
        List<Worker> workersList = new LinkedList<>();
        workersList.add(new Worker(3, 1, "Ben", "Johnson", "benny.com"));
        int response = workerRepository.save(workersList);

        assertEquals(202, response);
    }

    @Test
    void updateReturnsNumbOfChangeRowsSoShouldBeZeroIfWeChangeNothing() {
        int idOfWorkerToUpdate = 3;
        var worker = workerRepository.getById(idOfWorkerToUpdate);

        assertEquals(1, workerRepository.update(idOfWorkerToUpdate, worker));
    }

    @Test
    void isElementOfLibraryReturnsTrueBcThereIsIdOne() {
        assertTrue(workerRepository.isElementOfLibrary("worker", "id", 1));
    }
}