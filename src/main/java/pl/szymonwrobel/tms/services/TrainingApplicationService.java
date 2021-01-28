package pl.szymonwrobel.tms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.mappers.StudentUserMapper;
import pl.szymonwrobel.tms.mappers.TrainingApplicationMapper;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;
import pl.szymonwrobel.tms.repositories.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingApplicationService.class);

    private final TrainingApplicationRepository trainingApplicationRepository;
    private final TrainingApplicationMapper trainingApplicationMapper;
    private final UserRepository userRepository;
    private final StudentUserMapper studentUserMapper;

    public TrainingApplicationService(TrainingApplicationRepository trainingApplicationRepository,
                                      TrainingApplicationMapper trainingApplicationMapper,
                                      UserRepository userRepository,
                                      StudentUserMapper studentUserMapper) {
        this.trainingApplicationRepository = trainingApplicationRepository;
        this.trainingApplicationMapper = trainingApplicationMapper;
        this.userRepository = userRepository;
        this.studentUserMapper = studentUserMapper;
    }

    public void createApplicationForTraining(TrainingApplicationDTO trainingApplicationDTO) {
        TrainingApplicationEntity trainingApplicationEntity =
                trainingApplicationMapper.toEntity(trainingApplicationDTO);
        trainingApplicationRepository.save(trainingApplicationEntity);
        LOGGER.info("Użytkownik: " + trainingApplicationEntity.getUser().getLogin()
                + " złożył aplikację na kurs " + trainingApplicationEntity.getTraining().getName());
    }

    public List<TrainingApplicationDTO> getAllTrainingApplications() {
        final List<TrainingApplicationEntity> trainingApplicationEntities =
                trainingApplicationRepository.findAll();
        final List<TrainingApplicationDTO> trainingApplicationDTOs =
                trainingApplicationEntities
                        .stream()
                        .map(trainingApplicationMapper::toDto)
                        .sorted(Comparator.comparing(o -> o.getDate()))
                        .collect(Collectors.toList());
        return trainingApplicationDTOs;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTrainingApplication(Long id) {
        trainingApplicationRepository.deleteById(id);
        LOGGER.info("Usunięto aplikację ID: " + id);
    }

    private TrainingApplicationDTO getTrainingApplicationById(Long id) {
        final TrainingApplicationDTO trainingApplicationDTO = trainingApplicationRepository
                .findById(id)
                .map(trainingApplicationMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("No training application found "
                        + "with the training application id: " + id));
        return trainingApplicationDTO;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateTrainingApplication(Long id) {
        TrainingApplicationDTO trainingApplicationDTO = getTrainingApplicationById(id);
        trainingApplicationDTO.setIsConfirmed(true);
        TrainingApplicationEntity trainingApplicationEntity = trainingApplicationMapper
                .toEntity(trainingApplicationDTO);
        trainingApplicationRepository.saveAndFlush(trainingApplicationEntity);
        LOGGER.info("Zatwierdzono aplikację ID: " + trainingApplicationEntity.getId());
    }
}
