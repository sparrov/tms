package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.repositories.TrainingRepository;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ApplyTaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyTaskService.class);

    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingApplicationRepository trainingApplicationRepository;
    private final SecurityService securityService;
    private static Integer loginSuffix = 4;

    public ApplyTaskService(UserRepository userRepository, TrainingRepository trainingRepository,
                            TrainingApplicationRepository trainingApplicationRepository,
                            SecurityService securityService) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
        this.trainingApplicationRepository = trainingApplicationRepository;
        this.securityService = securityService;
    }

    @Scheduled(fixedRate = 30000, initialDelay = 10000)
    private void sendApplication() {
        String login = "student" + loginSuffix++;
        Long sizeOfTrainingList = trainingRepository.count();
        if (sizeOfTrainingList < 1) {
            throw new IllegalArgumentException("Nie znaleziono kursów W bazie danych");
        }
        Long randomID = ThreadLocalRandom.current().nextLong(1, sizeOfTrainingList + 1);
        TrainingEntity trainingEntity = trainingRepository.findById(randomID).orElse(null);

        UserEntity randomGeneratedStudentUser = new UserEntity(null, login, securityService.encodeUserPassword(login), UserType.STUDENT, true, login, login, List.of(new SimpleGrantedAuthority("STUDENT")), null);
        UserEntity newUserEntity = userRepository.save(randomGeneratedStudentUser);
        LOGGER.info("Utworzono użytkownika: " + randomGeneratedStudentUser.getLogin());

        TrainingApplicationEntity trainingApplication = new TrainingApplicationEntity(null, LocalDate.now(), trainingEntity, newUserEntity, false);
        trainingApplicationRepository.save(trainingApplication);
        LOGGER.info("Otrzymano aplikację od użytkownika " + newUserEntity.getLogin() + " na kurs " + trainingEntity.getName());
    }
}
