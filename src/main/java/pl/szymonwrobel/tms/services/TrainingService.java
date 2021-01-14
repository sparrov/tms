package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.List;

@Service
public class TrainingService {

    public final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<TrainingEntity> getAllTrainings() {
        return trainingRepository.findAll();
    }

}
