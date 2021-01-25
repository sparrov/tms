package pl.szymonwrobel.tms.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.szymonwrobel.tms.dtos.TrainingApplicationDTO;
import pl.szymonwrobel.tms.entities.TrainingApplicationEntity;
import pl.szymonwrobel.tms.mappers.TrainingApplicationMapper;
import pl.szymonwrobel.tms.repositories.TrainingApplicationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingApplicationService {

    private final TrainingApplicationRepository trainingApplicationRepository;
    private final TrainingApplicationMapper trainingApplicationMapper;

    public TrainingApplicationService(TrainingApplicationRepository trainingApplicationRepository,
                                      TrainingApplicationMapper trainingApplicationMapper) {
        this.trainingApplicationRepository = trainingApplicationRepository;
        this.trainingApplicationMapper = trainingApplicationMapper;
    }

    public void createApplicationForTraining(TrainingApplicationDTO trainingApplicationDTO) {
        TrainingApplicationEntity trainingApplicationEntity =
                trainingApplicationMapper.mapDtoToEntity(trainingApplicationDTO);
        trainingApplicationRepository.save(trainingApplicationEntity);
    }

    public List<TrainingApplicationDTO> getAllTrainingApplications() {
        final List<TrainingApplicationEntity> trainingApplicationEntities =
                trainingApplicationRepository.findAll();
        final List<TrainingApplicationDTO> trainingApplicationDTOs =
                trainingApplicationEntities
                        .stream()
                        .map(trainingApplicationMapper::mapEntityToDto)
                        .sorted(Comparator.comparing(o -> o.getDate()))
                        .collect(Collectors.toList());
        return trainingApplicationDTOs;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTrainingApplication(Long id) {
        trainingApplicationRepository.deleteById(id);
    }

    public TrainingApplicationDTO findTrainingApplicationById(Long id) {
        final TrainingApplicationDTO trainingApplicationDTO = trainingApplicationRepository
                .findById(id)
                .map(trainingApplicationMapper::mapEntityToDto)
                .orElseThrow(() -> new IllegalArgumentException("No training application found "
                        + "with the training application id: " + id));
        return trainingApplicationDTO;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateTrainingApplication(Long id) {
        TrainingApplicationDTO trainingApplicationDTO = findTrainingApplicationById(id);
        trainingApplicationDTO.setIsConfirmed("zatwierdzona");
        TrainingApplicationEntity trainingApplicationEntity = trainingApplicationMapper
                .mapDtoToEntity(trainingApplicationDTO);
        trainingApplicationRepository.saveAndFlush(trainingApplicationEntity);
    }
}
