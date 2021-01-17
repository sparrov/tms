package pl.szymonwrobel.tms.dtos;

import java.util.List;

public class TrainingDTO {

    private Long id;
    private String name;
    private List<Long> applicationsIds;

    public TrainingDTO() {
    }

    public TrainingDTO(Long id, String name, List<Long> applicationsIds) {
        this.id = id;
        this.name = name;
        this.applicationsIds = applicationsIds;
    }

    public Long getId() {
        return id;
    }

    public TrainingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<Long> getApplications() {
        return applicationsIds;
    }

    public TrainingDTO setApplications(List<Long> applicationsIds) {
        this.applicationsIds = applicationsIds;
        return this;
    }
}
