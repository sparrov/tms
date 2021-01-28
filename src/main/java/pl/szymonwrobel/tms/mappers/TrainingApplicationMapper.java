package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.repositories.TrainingRepository;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class TrainingApplicationMapper {

    private final StudentUserMapper studentUserMapper;
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    public TrainingApplicationMapper(StudentUserMapper studentUserMapper,
                                     TrainingRepository trainingRepository,
                                     UserRepository userRepository) {
        this.studentUserMapper = studentUserMapper;
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
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
        UserEntity newUserEntity = studentUserMapper.toEntity(trainingApplicationDTO
                .getStudentUserDTO());
        UserEntity userEntity = userRepository.save(newUserEntity);
        trainingApplicationEntity.setUser(userEntity);
        trainingApplicationEntity.setIsConfirmed(trainingApplicationDTO
                .getIsConfirmed() != null);
        return trainingApplicationEntity;
    }
}
