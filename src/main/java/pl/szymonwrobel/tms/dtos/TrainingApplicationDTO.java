package pl.szymonwrobel.tms.dtos;

public class TrainingApplicationDTO {

    private Long id;
    private StudentUserDTO studentUserDTO;
    private Long trainingId;

    public TrainingApplicationDTO() {
    }

    public TrainingApplicationDTO(Long id, StudentUserDTO studentUserDTO, Long trainingId) {
        this.id = id;
        this.studentUserDTO = studentUserDTO;
        this.trainingId = trainingId;
    }

    public Long getId() {
        return id;
    }

    public TrainingApplicationDTO setId(Long id) {
        this.id = id;
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
}
