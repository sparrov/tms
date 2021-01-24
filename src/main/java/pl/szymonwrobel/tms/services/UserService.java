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
//TODO: sprawdzić, czy jest OK
    public void createTrainerUser(TrainerUserDTO trainerUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newTrainerUserEntity = trainerUserMapper.mapDtoToEntity(trainerUserDTO);
        if (isUserLoginUnique(newTrainerUserEntity)) {
            userRepository.save(newTrainerUserEntity);
        } else {
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newTrainerUserEntity.getLogin() + " is not valid");
        }

/*        Boolean usersLogin = userRepository
                .findAll()
                .stream()
                .anyMatch(u -> u.getLogin().equalsIgnoreCase(newUserEntity.getLogin()));*/
    }

    public void createStudentUser(StudentUserDTO studentUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newStudentUserEntity = studentUserMapper.mapDtoToEntity(studentUserDTO);
        userRepository.save(newStudentUserEntity);
        if (isUserLoginUnique(newStudentUserEntity)) {
            userRepository.save(newStudentUserEntity);
        } else {
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newStudentUserEntity.getLogin() + " is not valid");
        }
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

    public Boolean isUserLoginUnique(UserEntity newUserEntity) {
        final List<UserEntity> listOfAllUsersLogins = userRepository.findUserEntitiesByLogin(newUserEntity.getLogin());
        Boolean isLoginUnique = listOfAllUsersLogins
                .stream()
                .noneMatch(u -> u.getLogin().equals(newUserEntity.getLogin()));
        return isLoginUnique;
    }
}
