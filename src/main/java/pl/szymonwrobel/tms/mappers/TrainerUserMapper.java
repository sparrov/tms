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

    public TrainerUserDTO toDto(UserEntity userEntity) {
        final TrainerUserDTO trainerUserDTO = new TrainerUserDTO();
        trainerUserDTO.setId(userEntity.getId());
        trainerUserDTO.setLogin(userEntity.getLogin());
        trainerUserDTO.setPassword(userEntity.getPassword());
        trainerUserDTO.setFirstName(userEntity.getFirstName());
        trainerUserDTO.setLastName(userEntity.getLastName());
        trainerUserDTO.setIsActive(userEntity.getIsActive());
        trainerUserDTO.setUserType(userEntity.getUserType().getDisplayName());
        return trainerUserDTO;
    }

    public UserEntity toEntity(TrainerUserDTO trainerUserDTO) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setLogin(trainerUserDTO.getLogin().toLowerCase().replace(" ", ""));
        userEntity.setPassword(securityService.encodeUserPassword(trainerUserDTO));
        userEntity.setFirstName(trainerUserDTO.getFirstName().replace(" ", ""));
        userEntity.setLastName(trainerUserDTO.getLastName().replace(" ", ""));
        userEntity.setIsActive(trainerUserDTO.getIsActive().equals(true));
        userEntity.setUserType(UserType.TRAINER);
        return userEntity;
    }
}
