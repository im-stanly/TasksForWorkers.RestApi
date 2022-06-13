package pl.tasksForWorkers.RestApi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tasksForWorkers.RestApi.model.Worker;
import pl.tasksForWorkers.RestApi.repository.WorkerRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> getWorkers(){
        return workerRepository.getWorkers();
    }

    public Worker getById(int id){
        return workerRepository.getById(id);
    }

    public Worker getByName(String name){
        return workerRepository.getByName(name);
    }

    public int save(List<Worker> workers){
        return workerRepository.save(workers);
    }

    public int update(int id, Worker worker){
        return patchOldWorkerWithNewWorker(id, worker, false);
    }

    public int patchUpdate(int id, Worker updatedWorker){
        return patchOldWorkerWithNewWorker(id, updatedWorker, true);
    }

    public int delete(int id){
        return workerRepository.delete(id);
    }

    private int patchOldWorkerWithNewWorker(int id, Worker updatedWorker, boolean isPatchUpdate){
        Worker oldWorker = workerRepository.getById(id);
        if (isPatchUpdate){
            if (updatedWorker.getName() != null)
                oldWorker.setName(updatedWorker.getName());
            if (updatedWorker.getLastName() != null)
                oldWorker.setLastName(updatedWorker.getLastName());
            if (updatedWorker.getE_mail() != null)
                oldWorker.setE_mail(updatedWorker.getE_mail());
        }
        else
            oldWorker = updatedWorker;
        return workerRepository.update(id, oldWorker);
    }
}
