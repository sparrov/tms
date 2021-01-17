package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.Optional;

@Component
public class TrainingApplicationMapper {

    private final UserMapper userMapper;
    private final StudentUserMapper studentUserMapper;
    private final TrainingRepository trainingRepository;

    public TrainingApplicationMapper(UserMapper userMapper, StudentUserMapper studentUserMapper, TrainingRepository trainingRepository) {
        this.userMapper = userMapper;
        this.studentUserMapper = studentUserMapper;
        this.trainingRepository = trainingRepository;
    }

    public TrainingApplicationDTO mapEntityToDto(TrainingApplicationEntity trainingApplicationEntity) {
        final TrainingApplicationDTO trainingApplicationDTO = new TrainingApplicationDTO();
        trainingApplicationDTO.setId(trainingApplicationEntity.getId());
        trainingApplicationDTO.setStudentUserDTO(userMapper.mapEntityToStudentUserDto(trainingApplicationEntity.getUser()));
        trainingApplicationDTO.setTrainingId(trainingApplicationEntity.getTraining().getId());
        return trainingApplicationDTO;
    }

    public TrainingApplicationEntity mapDtoToEntity(TrainingApplicationDTO trainingApplicationDTO) {
        final TrainingApplicationEntity trainingApplicationEntity = new TrainingApplicationEntity();
        trainingApplicationEntity.setId(trainingApplicationDTO.getId());
        //TODO: czy tak może być?
        Optional<TrainingEntity> trainingEntity = trainingRepository
                .findById(trainingApplicationDTO.getTrainingId());
        trainingApplicationEntity.setTraining(trainingEntity
                .orElseThrow(() -> new RuntimeException("training doesn't exist")));
        trainingApplicationEntity.setUser(studentUserMapper.mapDtoToEntity(trainingApplicationDTO.getStudentUserDTO()));
        return trainingApplicationEntity;
    }
}
