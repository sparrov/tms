package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TrainingApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private TrainingEntity training;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
    private UserEntity user;

    private Boolean isConfirmed;

    public TrainingApplicationEntity() {
    }

    public TrainingApplicationEntity(Long id, TrainingEntity training, UserEntity user,
                                     Boolean isConfirmed) {
        this.id = id;
        this.training = training;
        this.user = user;
        this.isConfirmed = isConfirmed;
    }

    public Long getId() {
        return id;
    }

    public TrainingApplicationEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public TrainingEntity getTraining() {
        return training;
    }

    public TrainingApplicationEntity setTraining(TrainingEntity training) {
        this.training = training;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public TrainingApplicationEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public Boolean getIsConfirmed() {
        return isConfirmed;
    }

    public TrainingApplicationEntity setIsConfirmed(Boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
        return this;
    }
}

