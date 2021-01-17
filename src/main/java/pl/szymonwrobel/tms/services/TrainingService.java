package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.mappers.TrainingMapper;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    public final TrainingRepository trainingRepository;
    public final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public List<TrainingDTO> getAllTrainings() {
        final List<TrainingEntity> allTrainingsEntities = trainingRepository.findAll();
        final List<TrainingDTO> allTrainingsDTOs = allTrainingsEntities
                .stream()
                .map(trainingMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allTrainingsDTOs;
    }

}
