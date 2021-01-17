package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;

@Component
public class UserMapper {

    public StudentUserDTO mapEntityToStudentUserDto(UserEntity userEntity){
        final StudentUserDTO studentUserDTO = new StudentUserDTO();
        studentUserDTO.setId(userEntity.getId());
        studentUserDTO.setLogin(userEntity.getLogin());
        studentUserDTO.setPassword(userEntity.getPassword());
        studentUserDTO.setFirstName(userEntity.getFirstName());
        studentUserDTO.setLastName(userEntity.getLastName());
        studentUserDTO.setIsActive("nieaktywny");
        studentUserDTO.setUserTypeDescription(UserType.STUDENT.getDisplayName());
        return studentUserDTO;
    }

}
