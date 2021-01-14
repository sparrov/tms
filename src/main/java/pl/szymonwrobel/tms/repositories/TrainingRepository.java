package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szymonwrobel.tms.entities.TrainingEntity;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity, Long> {

}
