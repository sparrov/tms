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

    private UserEntity adminUser;

    public void createSampleUsers() {
        adminUser = userRepository.save(new UserEntity(null, "admin", "test", UserType.ADMIN, true, "Administator", "Admistratorski"));
    }

    @Override
    @Transactional
    public void run(String... args) {
        createSampleUsers();
    }

}
