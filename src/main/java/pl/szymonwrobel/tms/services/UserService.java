package pl.szymonwrobel.tms.services;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.UserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.mappers.UserMapper;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.security.SecureRandom;
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

    public String encodeUserPassword(UserEntity userEntity){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(userEntity.getPassword());
        return encodedPassword;
    }

    public void createTrainerUser(UserDTO userDTO){
        UserEntity userEntity = userMapper.mapDtoToEntity(userDTO);
        userRepository.save(userEntity);
    }

    public List<UserDTO> getAllTrainerUsers() {
        final List<UserEntity> allTrainerUsersEntities = userRepository
                .findAll()
                .stream()
                .filter(userEntity -> userEntity.getUserType().equals(UserType.TRAINER))
                .collect(Collectors.toList());

        final List<UserDTO> allTrainerUsersDTO = allTrainerUsersEntities
                .stream()
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allTrainerUsersDTO;
    }
}
