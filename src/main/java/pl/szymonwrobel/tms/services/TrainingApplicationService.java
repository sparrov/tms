package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;

@Service
public class TrainingApplicationService {

    public final TrainingApplicationRepository trainingApplicationRepository;

    public TrainingApplicationService(TrainingApplicationRepository trainingApplicationRepository) {
        this.trainingApplicationRepository = trainingApplicationRepository;
    }
//TODO:
    public void createApplicationForTraining(TrainingApplicationEntity trainingApplicationEntity) {
        trainingApplicationRepository.save(trainingApplicationEntity);
    }
}
