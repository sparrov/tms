package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.StudentUserDTO;
import pl.szymonwrobel.tms.dtos.TrainerUserDTO;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.mappers.StudentUserMapper;
import pl.szymonwrobel.tms.mappers.TrainerUserMapper;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TrainerUserMapper userMapper;
    private final StudentUserMapper studentUserMapper;

    public UserService(UserRepository userRepository, TrainerUserMapper userMapper, StudentUserMapper studentUserMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.studentUserMapper = studentUserMapper;
    }

    public void createTrainerUser(TrainerUserDTO trainerUserDTO){
        UserEntity userEntity = userMapper.mapDtoToEntity(trainerUserDTO);
        userRepository.save(userEntity);
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
                .map(userMapper::mapEntityToDto)
                .collect(Collectors.toList());
        return allTrainerUsersDTOs;
    }

    public void deleteUser(Long id){
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
}
