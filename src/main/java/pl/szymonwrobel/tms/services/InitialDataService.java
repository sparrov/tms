package pl.szymonwrobel.tms.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;
import pl.szymonwrobel.tms.repositories.UserRepository;

@Service
public class InitialDataService implements CommandLineRunner {

    private final UserRepository userRepository;

    public InitialDataService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserEntity adminUser1;
    private UserEntity adminUser2;
    private UserEntity trainer1;
    private UserEntity trainer2;
    private UserEntity student1;
    private UserEntity student2;

    public void createSampleUsers() {
        adminUser1 = userRepository.save(new UserEntity(null, "admin1", "test", UserType.ADMIN, true, "Administator", "Admistratorski"));
        adminUser1 = userRepository.save(new UserEntity(null, "trainer1", "test", UserType.TRAINER, true, "Trener", "Trenerski"));
        adminUser1 = userRepository.save(new UserEntity(null, "student1", "test", UserType.STUDENT, true, "Student", "Studentski"));
    }

    @Override
    @Transactional
    public void run(String... args) {
        createSampleUsers();
    }

}
