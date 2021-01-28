package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TrainingMapper {

    final TrainingRepository trainingRepository;
    final TrainingApplicationRepository trainingApplicationRepository;

    public TrainingMapper(TrainingRepository trainingRepository,
                          TrainingApplicationRepository trainingApplicationRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingApplicationRepository = trainingApplicationRepository;
    }

    public TrainingDTO toDto(TrainingEntity trainingEntity) {
        final TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setId(trainingEntity.getId());
        trainingDTO.setName(trainingEntity.getName());
        final List<Long> listOfApplicationIds = trainingEntity
                .getApplications()
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toList());
        trainingDTO.setApplicationIds(listOfApplicationIds);
        return trainingDTO;
    }

    public TrainingEntity toEntity(TrainingDTO trainingDTO) {
        final TrainingEntity trainingEntity = new TrainingEntity();
        trainingEntity.setName(trainingDTO.getName());

        if (trainingDTO.getApplicationIds() == null) {
            trainingEntity.setApplications(Collections.emptySet());
        } else {
            final Set<TrainingApplicationEntity> allTrainingApplications =
                    new HashSet<>(trainingApplicationRepository
                            .findAllById(trainingDTO.getApplicationIds()));
            trainingEntity.setApplications(allTrainingApplications);
        }
        return trainingEntity;
    }
}
