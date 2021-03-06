package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymonwrobel.tms.entities.*;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.repositories.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Service
public class InitialDataService implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataService.class);

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final TrainingRepository trainingRepository;
    private final BlockRepository blockRepository;
    private final ClassesRepository classesRepository;
    private final TrainingApplicationRepository trainingApplicationRepository;

    public InitialDataService(UserRepository userRepository, SecurityService securityService, TrainingRepository trainingRepository, BlockRepository blockRepository, ClassesRepository classesRepository, TrainingApplicationRepository trainingApplicationRepository) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.trainingRepository = trainingRepository;
        this.blockRepository = blockRepository;
        this.classesRepository = classesRepository;
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
    private TrainingEntity training4;
    private TrainingApplicationEntity trainingApplication1;
    private TrainingApplicationEntity trainingApplication2;
    private TrainingApplicationEntity trainingApplication3;
    private TrainingApplicationEntity trainingApplication4;
    private BlockEntity block1;
    private BlockEntity block2;
    private BlockEntity block3;
    private BlockEntity block4;
    private BlockEntity block5;
    private BlockEntity block6;
    private BlockEntity block7;
    private BlockEntity block8;
    private ClassesEntity classes1;
    private ClassesEntity classes2;
    private ClassesEntity classes3;
    private ClassesEntity classes4;

    public void createAdminUser() {
        adminUser = new UserEntity(null, "admin", securityService.encodeUserPassword("test1234"), UserType.ADMIN, true, "Administator", "Admistratorski", List.of(new SimpleGrantedAuthority("ADMIN")), Collections.emptySet());
        userRepository.save(adminUser);
        LOGGER.info("Pomyślnie utworzono konto administratora systemu TMS");
    }

    public void createSampleTrainings() {
        training1 = new TrainingEntity(null, "Business English", Set.of(), Set.of());
        training1 = trainingRepository.save(training1);
        training2 = new TrainingEntity(null, "Java developer", Collections.emptySet(), Collections.emptySet());
        training2 = trainingRepository.save(training2);
        training3 = new TrainingEntity(null, "Docker", Collections.emptySet(), Collections.emptySet());
        training3 = trainingRepository.save(training3);
        training4 = new TrainingEntity(null, "FrontEnd developer", Collections.emptySet(), Collections.emptySet());
        training4 = trainingRepository.save(training4);
        LOGGER.info("Pomyślnie dodano podstawowe kursy w systemie TMS");
    }

    public void createSampleUsers() {
        trainerUser1 = new UserEntity(null, "trener1", securityService.encodeUserPassword("test1234"), UserType.TRAINER, true, "Prowadzący1", "Prowadzący1", List.of(new SimpleGrantedAuthority("TRAINER")), Collections.emptySet());
        userRepository.save(trainerUser1);
        trainerUser2 = new UserEntity(null, "trener2", securityService.encodeUserPassword("test1234"), UserType.TRAINER, true, "Prowadzący2", "Prowadzący2", List.of(new SimpleGrantedAuthority("TRAINER")), Collections.emptySet());
        userRepository.save(trainerUser2);
        trainerUser3 = new UserEntity(null, "trener3", securityService.encodeUserPassword("test1234"), UserType.TRAINER, true, "Prowadzący3", "Prowadzący3", List.of(new SimpleGrantedAuthority("TRAINER")), Collections.emptySet());
        userRepository.save(trainerUser3);
        studentUser1 = new UserEntity(null, "student1", securityService.encodeUserPassword("test1234"), UserType.STUDENT, true, "Uczestnik1", "Uczestnik1", List.of(new SimpleGrantedAuthority("STUDENT")), Collections.emptySet());
        userRepository.save(studentUser1);
        studentUser2 = new UserEntity(null, "student2", securityService.encodeUserPassword("test1234"), UserType.STUDENT, true, "Uczestnik2", "Uczestnik2", List.of(new SimpleGrantedAuthority("STUDENT")), Collections.emptySet());
        userRepository.save(studentUser2);
        studentUser3 = new UserEntity(null, "student3", securityService.encodeUserPassword("test1234"), UserType.STUDENT, true, "Uczestnik3", "Uczestnik3", List.of(new SimpleGrantedAuthority("STUDENT")), Collections.emptySet());
        userRepository.save(studentUser3);
        LOGGER.info("Pomyślnie utworzono testowe konta użytkowników systemu TMS");
    }

    public void createSampleApplications() {
        trainingApplication1 = new TrainingApplicationEntity(null, LocalDate.of(2020, 10, 07), training1, studentUser1, true);
        trainingApplicationRepository.save(trainingApplication1);
        trainingApplication2 = new TrainingApplicationEntity(null, LocalDate.of(2020, 12, 25), training2, studentUser2, true);
        trainingApplicationRepository.save(trainingApplication2);
        trainingApplication3 = new TrainingApplicationEntity(null, LocalDate.of(2021, 1, 21), training3, studentUser3, true);
        trainingApplicationRepository.save(trainingApplication3);
        trainingApplication4 = new TrainingApplicationEntity(null, LocalDate.of(2021, 1, 25), training2, studentUser1, false);
        trainingApplicationRepository.save(trainingApplication4);
        LOGGER.info("Pomyślnie złożono testowe aplikacje w systemie TMS");
    }

    public void createSampleClasses() {
        classes1 = new ClassesEntity(null, "Wprowadzenie", LocalDate.of(2021, 02, 01), LocalTime.of(8, 00), LocalTime.of(9, 00));
        classesRepository.save(classes1);
        classes2 = new ClassesEntity(null, "Środowisko programistyczne Javy", LocalDate.of(2021, 01, 02), LocalTime.of(9, 00), LocalTime.of(11, 00));
        classesRepository.save(classes2);
        classes3 = new ClassesEntity(null, "Podstawowe elementy języka Java - 1", LocalDate.of(2021, 01, 03), LocalTime.of(11, 00), LocalTime.of(13, 00));
        classesRepository.save(classes3);
        classes4 = new ClassesEntity(null, "Podstawowe elementy języka Java - 2", LocalDate.of(2021, 02, 04), LocalTime.of(13, 00), LocalTime.of(16, 00));
        classesRepository.save(classes4);
        LOGGER.info("Pomyślnie utworzono testowe zajęcia");
    }

    public void createSampleBlocks() {
        block1 = new BlockEntity(null, "Java - podstawy", training2, Set.of(classes1, classes2, classes3, classes4));
        blockRepository.save(block1);
        block2 = new BlockEntity(null, "Java - zaawansowana", training2, Collections.emptySet());
        blockRepository.save(block2);
        block3 = new BlockEntity(null, "Testowanie oprogramowania - podstawy", training2, Collections.emptySet());
        blockRepository.save(block3);
        block4 = new BlockEntity(null, "Testowanie oprogramowania - zaawansowane", training2, Collections.emptySet());
        blockRepository.save(block4);
        block5 = new BlockEntity(null, "Podstawy HTML", training4, Collections.emptySet());
        blockRepository.save(block5);
        block6 = new BlockEntity(null, "Podstawy CSS", training4, Collections.emptySet());
        blockRepository.save(block6);
        block7 = new BlockEntity(null, "Podstawy Javascript", training4, Collections.emptySet());
        blockRepository.save(block7);
        block8 = new BlockEntity(null, "Angular", training4, Collections.emptySet());
        blockRepository.save(block8);
        LOGGER.info("Pomyślnie utworzono testowe bloki zajęć");
    }

    @Override
    @Transactional
    public void run(String... args) {
        createAdminUser();
        createSampleTrainings();
        createSampleUsers();
        createSampleApplications();
        createSampleClasses();
        createSampleBlocks();
    }
}