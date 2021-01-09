package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.services.SecurityService;

@Component
public class UserMapper {

/*    private final UserService userService;

    public UserMapper(@Lazy UserService userService) {//dlaczego @Lazy? Czy nie lepiej odseparować enkoder?
        this.userService = userService;
    }*/

    private final SecurityService securityService;

    public UserMapper(SecurityService securityService) {
        this.securityService = securityService;
    }


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
        userEntity.setPassword(securityService.encodeUserPassword(userDTO));
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setIsActive(userDTO.getIsActive());
        userEntity.setUserType(UserType.TRAINER);
        return userEntity;
    }

}
