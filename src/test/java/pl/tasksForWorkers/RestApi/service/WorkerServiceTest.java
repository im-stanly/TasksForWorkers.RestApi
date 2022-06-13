package pl.tasksForWorkers.RestApi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tasksForWorkers.RestApi.exception.WorkerNotFoundException;
import pl.tasksForWorkers.RestApi.model.Worker;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkerServiceTest {

    @Autowired
    WorkerService workerService;

    @Test
    void updateWorkerWithIdOutOfOurRangeShouldThrowEx() {
        int idOutOfRange = 99;
        Worker randomWorker = workerService.getById(1);

        assertThrows(WorkerNotFoundException.class,
                () -> workerService.update(idOutOfRange, randomWorker));
    }

    @Test
    void updateWorkerShouldReplaceOldOne(){
        int idWorkerToUpdate = 3;
        var workerToUpdate = workerService.getById(idWorkerToUpdate);

        if (workerToUpdate.getTaskId() == null){
            workerToUpdate.setTaskId(2);
            workerService.update(idWorkerToUpdate, workerToUpdate);
        }

        workerToUpdate.setTaskId(null);
        workerService.update(idWorkerToUpdate, workerToUpdate);

        Worker workerAfterUpdate = workerService.getById(idWorkerToUpdate);

        assertEquals(null, workerAfterUpdate.getTaskId());
    }


    @Test
    void patchUpdateWorkerWithIdOutOfOurRangeShouldThrowEx() {
        int idOutOfRange = 99;
        Worker randomWorker = workerService.getById(1);

        assertThrows(WorkerNotFoundException.class,
                () -> workerService.patchUpdate(idOutOfRange, randomWorker));
    }

    @Test
    void patchUpdateShouldChangeOnlyNotNullPropertiesofWorker(){
        int idWorkerToUpdate = 3;
        Worker workerToUpdate = workerService.getById(idWorkerToUpdate);

        var oldTaskId = workerToUpdate.getTaskId();
        workerToUpdate.setTaskId(null);
        workerService.patchUpdate(idWorkerToUpdate, workerToUpdate);

        Worker workerAfterPatchUpdate = workerService.getById(idWorkerToUpdate);

        assertEquals(oldTaskId, workerAfterPatchUpdate.getTaskId());
    }
}