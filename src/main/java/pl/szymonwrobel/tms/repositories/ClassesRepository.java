package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymonwrobel.tms.entities.ClassesEntity;

public interface ClassesRepository extends JpaRepository<ClassesEntity, Long> {
}
