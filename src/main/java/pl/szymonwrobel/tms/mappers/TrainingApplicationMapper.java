package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class TrainingApplicationMapper {

    private final StudentUserMapper studentUserMapper;
    private final TrainingRepository trainingRepository;

    public TrainingApplicationMapper(StudentUserMapper studentUserMapper,
                                     TrainingRepository trainingRepository) {
        this.studentUserMapper = studentUserMapper;
        this.trainingRepository = trainingRepository;
    }

    public TrainingApplicationDTO toDto(TrainingApplicationEntity trainingApplicationEntity) {
        final TrainingApplicationDTO trainingApplicationDTO = new TrainingApplicationDTO();
        trainingApplicationDTO.setId(trainingApplicationEntity.getId());
        trainingApplicationDTO.setDate(trainingApplicationEntity.getDate());
        trainingApplicationDTO.setStudentUserDTO(studentUserMapper
                .toDto(trainingApplicationEntity.getUser()));
        trainingApplicationDTO.setTrainingId(trainingApplicationEntity.getTraining().getId());
        trainingApplicationDTO.setTrainingName(trainingApplicationEntity.getTraining().getName());
        trainingApplicationDTO.setIsConfirmed(trainingApplicationEntity.getIsConfirmed());
        return trainingApplicationDTO;
    }

    public TrainingApplicationEntity toEntity(TrainingApplicationDTO trainingApplicationDTO) {
        final TrainingApplicationEntity trainingApplicationEntity = new TrainingApplicationEntity();
        trainingApplicationEntity.setId(trainingApplicationDTO.getId());
        trainingApplicationEntity.setDate(LocalDate.now());
        Optional<TrainingEntity> trainingEntity = trainingRepository
                .findById(trainingApplicationDTO.getTrainingId());
        trainingApplicationEntity.setTraining(trainingEntity
                .orElseThrow(() -> new RuntimeException("Training doesn't exist")));
        trainingApplicationEntity.setUser(studentUserMapper
                .toEntity(trainingApplicationDTO.getStudentUserDTO()));
        trainingApplicationEntity.setIsConfirmed(trainingApplicationDTO
                .getIsConfirmed() == null ? false : true);
        return trainingApplicationEntity;
    }
}
