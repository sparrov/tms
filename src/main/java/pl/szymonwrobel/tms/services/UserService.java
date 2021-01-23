package pl.szymonwrobel.tms.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.exceptions.UserAlreadyExistAuthenticationException;
import pl.szymonwrobel.tms.mappers.StudentUserMapper;
import pl.szymonwrobel.tms.mappers.TrainerUserMapper;
import pl.szymonwrobel.tms.mappers.UserMapper;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TrainerUserMapper trainerUserMapper;
    private final StudentUserMapper studentUserMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, TrainerUserMapper trainerUserMapper, StudentUserMapper studentUserMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.trainerUserMapper = trainerUserMapper;
        this.studentUserMapper = studentUserMapper;
        this.userMapper = userMapper;
    }

    public void createTrainerUser(TrainerUserDTO trainerUserDTO) throws UserAlreadyExistAuthenticationException {
        //sprawdzić czy user jest w bazie - jeżeli TAK, rzucić wyjątek!
        UserEntity newUserEntity = trainerUserMapper.mapDtoToEntity(trainerUserDTO);
        Boolean usersLogin = userRepository
                .findAll()
                .stream()
                .anyMatch(u -> u.getLogin().equalsIgnoreCase(newUserEntity.getLogin()));

        if (usersLogin == false) {
            userRepository.save(newUserEntity);
        }

    }

    public void createStudentUser(StudentUserDTO studentUserDTO) {
        UserEntity userEntity = studentUserMapper.mapDtoToEntity(studentUserDTO);
        userRepository.save(userEntity);
    }

    public List<TrainerUserDTO> getAllTrainerUsers() {
        final List<UserEntity> allTrainerUsersEntities = userRepository
                .findAllByUserType(UserType.TRAINER);

        final List<TrainerUserDTO> allTrainerUsersDTOs = allTrainerUsersEntities
                .stream()
                .map(trainerUserMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allTrainerUsersDTOs;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public List<StudentUserDTO> getAllStudentUsers() {
        List<UserEntity> allStudentUsersEntities = userRepository.findAllByUserType(UserType.STUDENT);

        final List<StudentUserDTO> allStudentUsersDTOs = allStudentUsersEntities
                .stream()
                .map(studentUserMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allStudentUsersDTOs;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return userMapper.mapEntityToDto(userEntity);
    }
}
