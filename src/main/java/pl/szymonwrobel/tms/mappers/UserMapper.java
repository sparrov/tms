package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.services.SecurityService;

@Component
public class UserMapper {

    private final SecurityService securityService;

    public UserMapper(SecurityService securityService) {
        this.securityService = securityService;
    }


    public UserDTO mapEntityToDto(UserEntity userEntity) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setLogin(userEntity.getLogin());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setFirstName(userEntity.getFirstName());
        userDTO.setLastName(userEntity.getLastName());
        String status = "nieaktywne";
        if (userEntity.getIsActive()) {
            status = "aktywne";
        }
        userDTO.setIsActive(status);
        userDTO.setUserType(userEntity.getUserType().getDisplayName());
        return userDTO;
    }

    public UserEntity mapDtoToEntity(UserDTO userDTO) {

        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(securityService.encodeUserPassword(userDTO));
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        Boolean status = false;
        if (userDTO.getIsActive().equals("aktywne")) {
            status = true;
        }
        userEntity.setIsActive(status);
        userEntity.setUserType(UserType.TRAINER);
        return userEntity;
    }

}
