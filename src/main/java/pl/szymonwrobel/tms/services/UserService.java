package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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

    private Boolean isUserLoginUnique(UserEntity newUserEntity) {
        final Boolean isLoginUnique = userRepository
                .findUserEntitiesByLogin(newUserEntity.getLogin())
                .isEmpty();
        return isLoginUnique;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void createTrainerUser(TrainerUserDTO trainerUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newTrainerUserEntity = trainerUserMapper.toEntity(trainerUserDTO);
        if (isUserLoginUnique(newTrainerUserEntity)) {
            userRepository.save(newTrainerUserEntity);
        } else {
            LOGGER.error("Dodanie nowego prowadzącego: " + newTrainerUserEntity.getLogin()
                    + " nie powiodło się");
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newTrainerUserEntity.getLogin() + " is not valid");
        }
        LOGGER.info("Dodano nowego prowadzącego: " + newTrainerUserEntity.getLogin());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void createStudentUser(StudentUserDTO studentUserDTO)
            throws UserAlreadyExistAuthenticationException {
        UserEntity newStudentUserEntity = studentUserMapper.toEntity(studentUserDTO);
        if (isUserLoginUnique(newStudentUserEntity)) {
            userRepository.save(newStudentUserEntity);
        } else {
            LOGGER.error("Dodanie nowego uczestnika: " + newStudentUserEntity.getLogin()
                    + " nie powiodło się");
            throw new UserAlreadyExistAuthenticationException("Login: "
                    + newStudentUserEntity.getLogin() + " is not valid");
        }
        LOGGER.info("Dodano nowego uczestnika: " + newStudentUserEntity.getLogin());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TrainerUserDTO> getAllTrainerUsers() {
        final List<UserEntity> allTrainerUsersEntities = userRepository
                .findAllByUserType(UserType.TRAINER);

        final List<TrainerUserDTO> allTrainerUsersDTOs = allTrainerUsersEntities
                .stream()
                .map(trainerUserMapper::toDto)
                .sorted(Comparator.comparing(o -> o.getLogin()))
                .collect(Collectors.toList());
        return allTrainerUsersDTOs;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(Long id) throws SecurityException {
        if (id.equals(1L)) {
            LOGGER.info("Próba usunięcia użytkownika niedozwolona!");
            throw new SecurityException("Operacja niedozwolona");
        } else {
            userRepository.deleteById(id);
            LOGGER.info("Usunięto użytkownika ID: " + id);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TRAINER')")
    public List<StudentUserDTO> getAllStudentUsers() {
        List<UserEntity> allStudentUsersEntities = userRepository.findAllByUserType(UserType.STUDENT);

        final List<StudentUserDTO> allStudentUsersDTOs = allStudentUsersEntities
                .stream()
                .map(studentUserMapper::toDto)
                .sorted(Comparator.comparing(o -> o.getLogin()))
                .collect(Collectors.toList());
        return allStudentUsersDTOs;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Could not find user" + login));
        return userMapper.toDto(userEntity);
    }

}
