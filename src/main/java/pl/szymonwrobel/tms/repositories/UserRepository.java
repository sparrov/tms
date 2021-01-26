package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szymonwrobel.tms.entities.UserEntity;
import pl.szymonwrobel.tms.enums.UserType;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUserType(UserType userType);

    Optional<UserEntity> findByLogin(String userLogin);

    List<UserEntity> findUserEntitiesByLogin(String userLogin);
}
