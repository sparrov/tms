package pl.szymonwrobel.tms.services;

import org.springframework.security.access.prepost.PreAuthorize;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final TrainerUserMapper trainerUserMapper;
    private final StudentUserMapper studentUserMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, TrainerUserMapper trainerUserMapper,
                       StudentUserMapper studentUserMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.trainerUserMapper = trainerUserMapper;
        this.studentUserMapper = studentUserMapper;
        this.userMapper = userMapper;
    }

    public Boolean isUserLoginUnique(UserEntity newUserEntity) {
        final Boolean isLoginUnique = userRepository
                .findUserEntitiesByLogin(newUserEntity.getLogin())
                .isEmpty();
        return isLoginUnique;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void createTrainerUser(TrainerUserDTO trainerUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newTrainerUserEntity = trainerUserMapper.mapDtoToEntity(trainerUserDTO);
        if (isUserLoginUnique(newTrainerUserEntity)) {
            userRepository.save(newTrainerUserEntity);
        } else {
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newTrainerUserEntity.getLogin() + " is not valid");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void createStudentUser(StudentUserDTO studentUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newStudentUserEntity = studentUserMapper.mapDtoToEntity(studentUserDTO);
        if (isUserLoginUnique(newStudentUserEntity)) {
            userRepository.save(newStudentUserEntity);
        } else {
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newStudentUserEntity.getLogin() + " is not valid");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TrainerUserDTO> getAllTrainerUsers() {
        final List<UserEntity> allTrainerUsersEntities = userRepository
                .findAllByUserType(UserType.TRAINER);

        final List<TrainerUserDTO> allTrainerUsersDTOs = allTrainerUsersEntities
                .stream()
                .map(trainerUserMapper::mapEntityToDto)
                .sorted(Comparator.comparing(o -> o.getLogin()))
                .collect(Collectors.toList());
        return allTrainerUsersDTOs;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TRAINER')")
    public List<StudentUserDTO> getAllStudentUsers() {
        List<UserEntity> allStudentUsersEntities = userRepository.findAllByUserType(UserType.STUDENT);

        final List<StudentUserDTO> allStudentUsersDTOs = allStudentUsersEntities
                .stream()
                .map(studentUserMapper::mapEntityToDto)
                .sorted(Comparator.comparing(o -> o.getLogin()))
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
