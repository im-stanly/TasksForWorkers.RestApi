package pl.tasksForWorkers.RestApi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tasksForWorkers.RestApi.exception.TaskNotFoundException;
import pl.tasksForWorkers.RestApi.model.Task;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskService taskService;

    @Test
    void updateTaskWithIdIsOutOurDbShouldThrowEx() {
        int idOutOfRange = 99;
        Task randomBodyOfTask = taskService.getById(1);

        assertThrows(TaskNotFoundException.class,
                () -> taskService.update(idOutOfRange, randomBodyOfTask));
    }

    @Test
    void updatedTaskShouldReplaceOldOne(){
        int idTaskToUpdate = 4;
        Task taskToUpdate = taskService.getById(idTaskToUpdate);
        taskToUpdate.setDescription("Description");
        var response = taskService.update(idTaskToUpdate, taskToUpdate);

        taskToUpdate.setDescription(null);
        response = taskService.update(idTaskToUpdate, taskToUpdate);
        Task taskAfterUpdate = taskService.getById(idTaskToUpdate);

        assertEquals(null, taskAfterUpdate.getDescription());
    }

    @Test
    void patchUpdateTaskWithIdIsOutOurDbShouldThrowEx() {
        int idOutOfRange = 99;
        Task randomBodyOfTask = taskService.getById(1);

        assertThrows(TaskNotFoundException.class,
                () -> taskService.patchUpdate(idOutOfRange, randomBodyOfTask));
    }

    @Test
    void patchUpdateTaskShouldNotChangeNullPropertiesOfTask() {
        int idTaskToUpdate = 4;
        Task taskToUpdate = taskService.getById(idTaskToUpdate);
        var oldDescriptionOfTask = taskToUpdate.getDescription();
        taskToUpdate.setDescription(null);

        var response = taskService.patchUpdate(idTaskToUpdate, taskToUpdate);

        assertEquals(oldDescriptionOfTask, taskToUpdate.getDescription());
    }
}