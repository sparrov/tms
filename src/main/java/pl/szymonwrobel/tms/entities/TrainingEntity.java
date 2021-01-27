package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "training", cascade = {CascadeType.ALL})
    private Set<TrainingApplicationEntity> applications = new HashSet<>();

    public TrainingEntity() {
    }

    public TrainingEntity(Long id, String name, Set<TrainingApplicationEntity> applications) {
        this.id = id;
        this.name = name;
        this.applications = applications;
    }

    public Long getId() {
        return id;
    }

    public TrainingEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TrainingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Set<TrainingApplicationEntity> getApplications() {
        return applications;
    }

    public TrainingEntity setApplications(Set<TrainingApplicationEntity> applications) {
        this.applications = applications;
        return this;
    }
}
