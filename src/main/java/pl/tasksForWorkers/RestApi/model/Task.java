package pl.tasksForWorkers.RestApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private String state;
    private LocalDate deathline;
    private List<Worker> workersList;
}
