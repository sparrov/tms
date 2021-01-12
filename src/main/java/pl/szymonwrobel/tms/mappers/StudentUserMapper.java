package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.services.SecurityService;

@Component
public class StudentUserMapper {

    private final SecurityService securityService;

    public StudentUserMapper(SecurityService securityService) {
        this.securityService = securityService;
    }

    public UserEntity mapDtoToEntity(StudentUserDTO studentUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(studentUserDTO.getLogin());
        userEntity.setPassword(securityService.encodeUserPassword(studentUserDTO));
        userEntity.setFirstName(studentUserDTO.getFirstName());
        userEntity.setLastName(studentUserDTO.getLastName());
        //TODO: a może można zrobić to lepiej?
        Boolean isActive = false;
        if (studentUserDTO.getIsActive() == null) {
            studentUserDTO.setIsActive("nieaktywne");
        }
        else if (studentUserDTO.getIsActive().equals("aktywne")){
            isActive = true;
        }
        userEntity.setIsActive(isActive);
        userEntity.setUserType(UserType.STUDENT);
        return userEntity;
    }

    public StudentUserDTO mapEntityToDto(UserEntity userEntity) {
        StudentUserDTO studentUserDTO = new StudentUserDTO();
        studentUserDTO.setId(userEntity.getId());
        studentUserDTO.setLogin(userEntity.getLogin());
        studentUserDTO.setPassword(userEntity.getPassword());
        studentUserDTO.setFirstName(userEntity.getFirstName());
        studentUserDTO.setLastName(userEntity.getLastName());
        String status = "nieaktywne";
        if (userEntity.getIsActive()) {
            status = "aktywne";
        }
        studentUserDTO.setIsActive(status);
        studentUserDTO.setUserTypeDescription(userEntity.getUserType().getDisplayName());
        return studentUserDTO;
    }
}