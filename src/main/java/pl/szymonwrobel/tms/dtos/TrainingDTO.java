package pl.szymonwrobel.tms.dtos;

import java.util.List;

public class TrainingDTO {

    private Long id;
    private String name;
    private List<Long> applicationIds;

    public TrainingDTO() {
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

    public List<Long> getApplicationIds() {
        return applicationIds;
    }

    public TrainingDTO setApplicationIds(List<Long> applicationIds) {
        this.applicationIds = applicationIds;
        return this;
    }
}
