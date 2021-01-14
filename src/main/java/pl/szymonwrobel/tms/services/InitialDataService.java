package pl.szymonwrobel.tms.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.repositories.TrainingRepository;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.util.Collections;

@Service
public class InitialDataService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final TrainingRepository trainingRepository;
    private final TrainingApplicationRepository trainingApplicationRepository;

    public InitialDataService(UserRepository userRepository, SecurityService securityService, TrainingRepository trainingRepository, TrainingApplicationRepository trainingApplicationRepository) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.trainingRepository = trainingRepository;
        this.trainingApplicationRepository = trainingApplicationRepository;
    }

    private UserEntity adminUser;
    private UserEntity trainerUser1;
    private UserEntity trainerUser2;
    private UserEntity trainerUser3;
    private UserEntity studentUser1;
    private UserEntity studentUser2;
    private UserEntity studentUser3;
    private TrainingEntity training1;
    private TrainingEntity training2;
    private TrainingEntity training3;
    private TrainingApplicationEntity trainingApplication1;
    private TrainingApplicationEntity trainingApplication2;
    private TrainingApplicationEntity trainingApplication3;
    private TrainingApplicationEntity trainingApplication4;

    public void createAdminUser() {
        adminUser = new UserEntity(null, "admin", securityService.encodeUserPassword("test"), UserType.ADMIN, true, "Administator", "Admistratorski", Collections.EMPTY_SET);
        userRepository.save(adminUser);
    }

    public void createSampleTrainings(){
        training1 = new TrainingEntity(null, "Business English", Collections.emptySet());
        trainingRepository.save(training1);
        training2 = new TrainingEntity(null, "Java", Collections.emptySet());
        trainingRepository.save(training2);
        training3 = new TrainingEntity(null, "Docker", Collections.emptySet());
        trainingRepository.save(training3);
    }

    public void createSampleUsers() {
        trainerUser1 = new UserEntity(null, "trenerUser1", securityService.encodeUserPassword("123456"), UserType.TRAINER, true, "Prowadzący1", "Prowadzący1", Collections.EMPTY_SET);
        userRepository.save(trainerUser1);
        trainerUser2 = new UserEntity(null, "trenerUser2", securityService.encodeUserPassword("123456"), UserType.TRAINER, true, "Prowadzący2", "Prowadzący2", Collections.EMPTY_SET);
        userRepository.save(trainerUser2);
        trainerUser3 = new UserEntity(null, "trenerUser3", securityService.encodeUserPassword("123456"), UserType.TRAINER, true, "Prowadzący3", "Prowadzący3", Collections.EMPTY_SET);
        userRepository.save(trainerUser3);
        studentUser1 = new UserEntity(null, "studentUser1", securityService.encodeUserPassword("123456"), UserType.STUDENT, true, "Student1", "Student1", Collections.EMPTY_SET);
        userRepository.save(studentUser1);
        studentUser2 = new UserEntity(null, "studentUser2", securityService.encodeUserPassword("123456"), UserType.STUDENT, true, "Student2", "Student2", Collections.EMPTY_SET);
        userRepository.save(studentUser2);
        studentUser3 = new UserEntity(null, "studentUser3", securityService.encodeUserPassword("123456"), UserType.STUDENT, true, "Student3", "Student3", Collections.EMPTY_SET);
        userRepository.save(studentUser3);
    }

    public void createSampleApplications(){
        trainingApplication1 = new TrainingApplicationEntity(null, training1, studentUser1);
        trainingApplicationRepository.save(trainingApplication1);
        trainingApplication2 = new TrainingApplicationEntity(null, training2, studentUser2);
        trainingApplicationRepository.save(trainingApplication2);
        trainingApplication3 = new TrainingApplicationEntity(null, training3, studentUser3);
        trainingApplicationRepository.save(trainingApplication3);
        trainingApplication4 = new TrainingApplicationEntity(null, training2, studentUser1);
        trainingApplicationRepository.save(trainingApplication4);
    }


    @Override
    @Transactional
    public void run(String... args) {
        createAdminUser();
        createSampleTrainings();
        createSampleUsers();
        createSampleApplications();
    }

}
