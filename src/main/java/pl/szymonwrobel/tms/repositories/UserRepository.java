package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymonwrobel.tms.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
