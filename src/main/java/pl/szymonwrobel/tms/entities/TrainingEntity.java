package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "trainings")
public class TrainingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<BlockEntity> blocks = new HashSet<>();

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Set<TrainingApplicationEntity> applications = new HashSet<>();

    public TrainingEntity() {
    }

    public TrainingEntity(Long id, @NotEmpty String name, Set<BlockEntity> blocks, Set<TrainingApplicationEntity> applications) {
        this.id = id;
        this.name = name;
        this.blocks = blocks;
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

    public Set<BlockEntity> getBlocks() {
        return blocks;
    }

    public TrainingEntity setBlocks(Set<BlockEntity> blocks) {
        this.blocks = blocks;
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
