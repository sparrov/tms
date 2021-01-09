package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;

@Component
public class UserMapper {

    public UserDTO mapEntityToDto(UserEntity userEntity) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setIsActive(userEntity.getIsActive());
        userDTO.setUserType(userEntity.getUserType());
        return userDTO;
    }

    public UserEntity mapDtoToEntity(UserDTO userDTO){
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setIsActive(userDTO.getIsActive());
        userEntity.setUserType(userDTO.getUserType());
        return userEntity;
    }

}
