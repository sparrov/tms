package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TrainingMapper {

    final TrainingRepository trainingRepository;
    final TrainingApplicationRepository trainingApplicationRepository;

    public TrainingMapper(TrainingRepository trainingRepository, TrainingApplicationRepository trainingApplicationRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingApplicationRepository = trainingApplicationRepository;
    }

    public TrainingDTO mapEntityToDto(TrainingEntity trainingEntity) {
        final TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setId(trainingEntity.getId());
        trainingDTO.setName(trainingEntity.getName());
        final List<Long> listOfApplicationsIds = trainingEntity
                .getApplications()
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        trainingDTO.setApplications(listOfApplicationsIds);
        return trainingDTO;
    }

    public TrainingEntity mapDtoToEntity(TrainingDTO trainingDTO){
        final TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setId(trainingDTO.getId());
        trainingEntity.setName(trainingDTO.getName());
        final Set<TrainingApplicationEntity> allTrainingsApplications = new HashSet<>(trainingApplicationRepository
                .findAllById(trainingDTO.getApplications()));
        trainingEntity.setApplications(allTrainingsApplications);
        return trainingEntity;
    }

}
