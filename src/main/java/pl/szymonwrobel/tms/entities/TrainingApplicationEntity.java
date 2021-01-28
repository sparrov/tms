package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "training_applications")
public class TrainingApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    private TrainingEntity training;

    @ManyToOne
    private UserEntity user;

    private Boolean isConfirmed;

    public TrainingApplicationEntity() {
    }

    public TrainingApplicationEntity(Long id, LocalDate date, TrainingEntity training,
                                     UserEntity user, Boolean isConfirmed) {
        this.id = id;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public TrainingApplicationEntity setDate(LocalDate date) {
        this.date = date;
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

