package pl.szymonwrobel.tms.dtos;

public class TrainingApplicationDTO {

    private Long id;
    private StudentUserDTO studentUserDTO;
    private Long trainingId;
    private String trainingName;
    private String isConfirmed;


    public TrainingApplicationDTO() {
    }

    public TrainingApplicationDTO(Long id, StudentUserDTO studentUserDTO, Long trainingId,
                                  String isConfirmed, String trainingName) {
        this.id = id;
        this.studentUserDTO = studentUserDTO;
        this.trainingId = trainingId;
        this.isConfirmed = isConfirmed;
        this.trainingName = trainingName;
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

    public String getTrainingName() {
        return trainingName;
    }

    public TrainingApplicationDTO setTrainingName(String trainingName) {
        this.trainingName = trainingName;
        return this;
    }

    public String getIsConfirmed() {
        return isConfirmed;
    }

    public TrainingApplicationDTO setIsConfirmed(String isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }
}
