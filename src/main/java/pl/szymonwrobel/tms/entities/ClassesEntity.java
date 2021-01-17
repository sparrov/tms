package pl.szymonwrobel.tms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ClassesEntity {

    @Id
    private Long id;
    private String subject;
    private LocalDateTime term;

    public ClassesEntity() {
    }

    public ClassesEntity(Long id, String subject, LocalDateTime term) {
        this.id = id;
        this.subject = subject;
        this.term = term;
    }
}