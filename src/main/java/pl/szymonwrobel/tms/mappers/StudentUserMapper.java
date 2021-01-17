package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.services.SecurityService;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentUserMapper {

    private final SecurityService securityService;
    private final TrainingApplicationRepository trainingApplicationRepository;

    public StudentUserMapper(SecurityService securityService, TrainingApplicationRepository trainingApplicationRepository) {
        this.securityService = securityService;
        this.trainingApplicationRepository = trainingApplicationRepository;
    }

    public UserEntity mapDtoToEntity(StudentUserDTO studentUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(studentUserDTO.getLogin());
        userEntity.setPassword(securityService.encodeUserPassword(studentUserDTO));
        userEntity.setFirstName(studentUserDTO.getFirstName());
        userEntity.setLastName(studentUserDTO.getLastName());
        userEntity.setIsActive(studentUserDTO.getIsActive() != null &&
                studentUserDTO.getIsActive().equals("aktywne"));
        userEntity.setUserType(UserType.STUDENT);
        final List<TrainingApplicationEntity> applies =
                trainingApplicationRepository
                        .findAllById(studentUserDTO.getAppliedTrainingsIds()
                                != null ? studentUserDTO.getAppliedTrainingsIds()
                                : Collections.emptyList());
        userEntity.setApplications(new HashSet<>(applies));
        return userEntity;
    }

    public StudentUserDTO mapEntityToDto(UserEntity userEntity) {
        StudentUserDTO studentUserDTO = new StudentUserDTO();
        studentUserDTO.setId(userEntity.getId());
        studentUserDTO.setLogin(userEntity.getLogin());
        studentUserDTO.setPassword(userEntity.getPassword());
        studentUserDTO.setFirstName(userEntity.getFirstName());
        studentUserDTO.setLastName(userEntity.getLastName());
        studentUserDTO.setIsActive(userEntity.getIsActive() ? "aktywne" : "nieaktywne");
        studentUserDTO.setUserTypeDescription(userEntity.getUserType().getDisplayName());
        final List<String> applies = userEntity
                .getApplications()
                .stream()
                .map(e -> e.getTraining().getName())
                .sorted()
                .collect(Collectors.toList());
        studentUserDTO.setAppliedTrainingsNames(applies);
        return studentUserDTO;
    }
}
