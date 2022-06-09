package pl.tasksForWorkers.RestApi.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.tasksForWorkers.RestApi.exception.WorkerNotFoundException;
import pl.tasksForWorkers.RestApi.model.Worker;

import java.util.List;

@Repository
public class WorkerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String GET_WORKER_PROPERTIES_SQL_CODE =  "SELECT id, taskId, name, lastName, e_mail FROM worker";
    private final String WORKER_PROPERTIES_SQL = "taskId, name, lastName, e_mail";

    public List<Worker> getWorkers(){
        return jdbcTemplate.query(GET_WORKER_PROPERTIES_SQL_CODE,
                BeanPropertyRowMapper.newInstance(Worker.class));
    }

    public Worker getById(int id){
        return getSingleWorkerByObject("id", id);
    }

    public List<Worker> getWorkersByTaskId(int taskId){
        return jdbcTemplate.query(GET_WORKER_PROPERTIES_SQL_CODE +
                " WHERE taskId=?", BeanPropertyRowMapper.newInstance(Worker.class), taskId);
    }

    public Worker getByName(String title){
        return getSingleWorkerByObject("title", title);
    }

    public int save(List<Worker> workers){
         workers.forEach(worker -> {
             jdbcTemplate.update("INSERT INTO worker(" + WORKER_PROPERTIES_SQL + ") "
                     + "VALUES(?, ?, ?, ?)",
                     worker.getTaskId(), worker.getName(),
                     worker.getLastName(), worker.getE_mail());
         }
        );
        return 202;
    }

    public int update(int id, Worker worker){
        return jdbcTemplate.update("UPDATE worker SET id=?, taskId=?, name=?, lastName=?, e_mail=? " +
                        "WHERE id = ?",
                worker.getId(), worker.getTaskId(), worker.getName(),
                worker.getLastName(), worker.getE_mail(), id);
    }

    public int delete(int id){
        if (!isElementOfLibrary("worker", "id", id))
            throw new WorkerNotFoundException(id);
        return jdbcTemplate.update("DELETE FROM worker WHERE id=?", id);
    }

    private Worker getSingleWorkerByObject(String kind, Object object){
        if (!isElementOfLibrary("worker", kind, object)){
            if (object.getClass().equals(String.class))
                throw new WorkerNotFoundException((String) object);
            else
                throw new WorkerNotFoundException((Integer) object);
        }
        return jdbcTemplate.queryForObject(GET_WORKER_PROPERTIES_SQL_CODE + " WHERE "
                + kind + "=?", BeanPropertyRowMapper.newInstance(Worker.class), object);
    }

    protected boolean isElementOfLibrary(String nameOfTableInDB, String kind, Object object){
        int elementsInDB = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM "
                        + nameOfTableInDB + " WHERE " + kind + " = ?",
                Integer.class, object);

        return elementsInDB == 1 ? true : false;
    }
}
