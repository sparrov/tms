package pl.szymonwrobel.tms.mappers;

import org.springframework.stereotype.Component;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.services.SecurityService;

@Component
public class TrainerUserMapper {

    private final SecurityService securityService;

    public TrainerUserMapper(SecurityService securityService) {
        this.securityService = securityService;
    }


    public TrainerUserDTO mapEntityToDto(UserEntity userEntity) {
        final TrainerUserDTO trainerUserDTO = new TrainerUserDTO();
        trainerUserDTO.setId(userEntity.getId());
        trainerUserDTO.setLogin(userEntity.getLogin());
        trainerUserDTO.setPassword(userEntity.getPassword());
        trainerUserDTO.setFirstName(userEntity.getFirstName());
        trainerUserDTO.setLastName(userEntity.getLastName());
        trainerUserDTO.setIsActive(userEntity.getIsActive() ? "aktywne" : "nieaktywne");
        trainerUserDTO.setUserType(userEntity.getUserType().getDisplayName());
        return trainerUserDTO;
    }

    public UserEntity mapDtoToEntity(TrainerUserDTO trainerUserDTO) {

        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(trainerUserDTO.getLogin());
        userEntity.setPassword(securityService.encodeUserPassword(trainerUserDTO));
        userEntity.setFirstName(trainerUserDTO.getFirstName());
        userEntity.setLastName(trainerUserDTO.getLastName());
        userEntity.setIsActive(trainerUserDTO.getIsActive().equals("aktywne") ? true : false);
        userEntity.setUserType(UserType.TRAINER);
        return userEntity;
    }

}
