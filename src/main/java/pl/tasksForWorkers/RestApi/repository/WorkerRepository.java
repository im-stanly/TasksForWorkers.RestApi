package pl.tasksForWorkers.RestApi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.util.List;

@Repository
public class WorkerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String GET_WORKER_PROPERTIES_SQL_CODE =  "SELECT id, taskId, name, lastName, e_mail FROM worker";

    public List<Worker> getWorkers(){
        return jdbcTemplate.query(GET_WORKER_PROPERTIES_SQL_CODE,
                BeanPropertyRowMapper.newInstance(Worker.class));
    }

    public Worker getById(int id){
        return getSingleWorkerByObject("id", id);
    }

    public Worker getByTitle(String title){
        return getSingleWorkerByObject("title", title);
    }

    private Worker getSingleWorkerByObject(String kind, Object object){
        return jdbcTemplate.queryForObject(GET_WORKER_PROPERTIES_SQL_CODE + " WHERE "
                + kind + "=?", BeanPropertyRowMapper.newInstance(Worker.class), object);
    }
}
