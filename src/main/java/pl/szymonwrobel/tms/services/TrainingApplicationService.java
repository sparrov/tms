package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.mappers.TrainingApplicationMapper;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;

@Service
public class TrainingApplicationService {

    private final TrainingApplicationRepository trainingApplicationRepository;
    private final TrainingApplicationMapper trainingApplicationMapper;

    public TrainingApplicationService(TrainingApplicationRepository trainingApplicationRepository, TrainingApplicationMapper trainingApplicationMapper) {
        this.trainingApplicationRepository = trainingApplicationRepository;
        this. trainingApplicationMapper = trainingApplicationMapper;
    }

    public void createApplicationForTraining(TrainingApplicationDTO trainingApplicationDTO) {
        TrainingApplicationEntity trainingApplicationEntity = trainingApplicationMapper.mapDtoToEntity(trainingApplicationDTO);
        trainingApplicationRepository.save(trainingApplicationEntity);
    }
}
