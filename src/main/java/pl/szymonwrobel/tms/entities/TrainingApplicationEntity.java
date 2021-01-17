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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserEntity user;

    public TrainingApplicationEntity() {
    }

    public TrainingApplicationEntity(Long id, TrainingEntity training, UserEntity user) {
        this.id = id;
        this.training = training;
        this.user = user;
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
}

