package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.TrainingDTO;
import pl.szymonwrobel.tms.entities.TrainingEntity;
import pl.szymonwrobel.tms.mappers.TrainingMapper;
import pl.szymonwrobel.tms.repositories.TrainingRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingService.class);

    public final TrainingRepository trainingRepository;
    public final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.trainingMapper = trainingMapper;
    }

    public List<TrainingDTO> getAllTrainings() {
        final List<TrainingEntity> allTrainingsEntities = trainingRepository.findAll();
        final List<TrainingDTO> allTrainingsDTOs = allTrainingsEntities
                .stream()
                .map(trainingMapper::toDto)
                .sorted(Comparator.comparing(o -> o.getName()))
                .collect(Collectors.toList());
        return allTrainingsDTOs;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void createTraining(TrainingDTO trainingDTO) {
        TrainingEntity newtrainingEntity = trainingMapper.toEntity(trainingDTO);
        trainingRepository.save(newtrainingEntity);
        LOGGER.info("Dodano nowy kurs: " + newtrainingEntity.getName());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTraining(Long id) {
        try {
            trainingRepository.deleteById(id);
            LOGGER.info("Usunięto kurs ID: " + id);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("Usunięcie kursu ID: " + id + " nie powiodło się");
            throw new EmptyResultDataAccessException("No training found "
                    + "with the training id: " + id, 1);//TODO: czym jest ta jedynka?
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','TRAINER','STUDENT')")
    public TrainingDTO getTrainingById(Long id) {
        TrainingDTO trainingDTO = trainingRepository
                .findById(id)
                .map(trainingMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("No training found "
                        + "with the training id: " + id));

        return trainingDTO;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateTraining(Long id, TrainingDTO trainingDTO) {
        TrainingEntity trainingEntity = trainingMapper.toEntity(trainingDTO);
        trainingRepository.saveAndFlush(trainingEntity.setId(id));
        LOGGER.info("Pomyślnie zaktualizowano kurs: " + trainingEntity.getName());
    }
}
