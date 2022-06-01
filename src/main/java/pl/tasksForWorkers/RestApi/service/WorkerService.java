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

    public Worker getBytitle(String title){
        return workerRepository.getByTitle(title);
    }
}
