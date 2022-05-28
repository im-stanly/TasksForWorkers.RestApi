package pl.tasksForWorkers.RestApi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tasksForWorkers.RestApi.model.Task;

import java.util.List;

@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Task> getAllTasks(){
        List<Task> tasks = jdbcTemplate.query("SELECT id, title, description, deathline FROM task",
                BeanPropertyRowMapper.newInstance(Task.class));
        return tasks;
    }
}
