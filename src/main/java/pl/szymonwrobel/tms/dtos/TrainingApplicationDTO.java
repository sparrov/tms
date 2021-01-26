package pl.szymonwrobel.tms.dtos;

import java.time.LocalDate;

public class TrainingApplicationDTO {

    private Long id;
    private LocalDate date;
    private StudentUserDTO studentUserDTO;
    private Long trainingId;
    private String trainingName;
    private Boolean isConfirmed;


    public TrainingApplicationDTO() {
    }

    public Long getId() {
        return id;
    }

    public TrainingApplicationDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TrainingApplicationDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public StudentUserDTO getStudentUserDTO() {
        return studentUserDTO;
    }

    public TrainingApplicationDTO setStudentUserDTO(StudentUserDTO studentUserDTO) {
        this.studentUserDTO = studentUserDTO;
        return this;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public TrainingApplicationDTO setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
        return this;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public TrainingApplicationDTO setTrainingName(String trainingName) {
        this.trainingName = trainingName;
        return this;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public TrainingApplicationDTO setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }
}
