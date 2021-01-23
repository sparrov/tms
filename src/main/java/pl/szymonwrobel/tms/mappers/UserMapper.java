package pl.szymonwrobel.tms.mappers;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;

import java.util.Arrays;

@Component
public class UserMapper {

    public UserDTO mapEntityToDto(UserEntity userEntity){
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        userDTO.setIsActive(userEntity.getIsActive());
        userDTO.setUserType(userEntity.getUserType().name());
        userDTO.getAuthorities();
        return userDTO;
    }

    public UserEntity mapDtoToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setIsActive(userDTO.getIsActive());
        userEntity.setUserType(UserType.valueOf(userDTO.getUserType()));
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userDTO.getUserType());
        userEntity.setAuthorities(Arrays.asList(authority));
        return userEntity;
    }

}
