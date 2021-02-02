package pl.szymonwrobel.tms.services;

import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.entities.BlockEntity;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.repositories.BlockRepository;

import java.util.List;
import java.util.Set;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

}
