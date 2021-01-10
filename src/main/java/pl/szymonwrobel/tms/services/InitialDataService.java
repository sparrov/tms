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
    private final SecurityService securityService;

    public InitialDataService(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    private UserEntity adminUser;

    public void createSampleUsers() {
        adminUser = new UserEntity(null, "admin", securityService.encodeUserPassword("test"), UserType.ADMIN, true, "Administator", "Admistratorski");
        userRepository.save(adminUser);
    }

    @Override
    @Transactional
    public void run(String... args) {
        createSampleUsers();
    }

}
