package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.mappers.UserMapper;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void createTrainerUser(UserDTO userDTO){
        UserEntity userEntity = userMapper.mapDtoToEntity(userDTO);
        userRepository.save(userEntity);
    }

    public List<UserDTO> getAllTrainerUsers() {
        final List<UserEntity> allTrainerUsersEntities = userRepository
                .findAllByUserType(UserType.TRAINER);

        final List<UserDTO> allTrainerUsersDTO = allTrainerUsersEntities
                .stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allTrainerUsersDTO;
    }
}
