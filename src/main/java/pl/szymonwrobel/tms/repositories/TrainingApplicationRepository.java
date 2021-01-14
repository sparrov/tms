package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;

@Repository
public interface TrainingApplicationRepository extends JpaRepository<TrainingApplicationEntity, Long> {
}
