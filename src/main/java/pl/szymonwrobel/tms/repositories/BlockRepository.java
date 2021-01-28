package pl.szymonwrobel.tms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.szymonwrobel.tms.entities.BlockEntity;

public interface BlockRepository extends JpaRepository<BlockEntity, Long> {
}
