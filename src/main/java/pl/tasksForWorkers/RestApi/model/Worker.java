package pl.tasksForWorkers.RestApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {
    private int id;
    private Integer taskId;
    private String name;
    private String lastName;
    private String e_mail;
}
