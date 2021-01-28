package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity(name = "classes")
public class ClassesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 60)
    private String subject;


    @Column(nullable = false)
    private LocalDate date;


    @Column(nullable = false)
    private LocalTime begin;


    @Column(nullable = false)
    private LocalTime end;


    public ClassesEntity() {
    }

    public ClassesEntity(Long id, @NotEmpty String subject, LocalDate date, LocalTime begin, LocalTime end) {
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public ClassesEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ClassesEntity setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ClassesEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public LocalTime getBegin() {
        return begin;
    }

    public ClassesEntity setBegin(LocalTime start) {
        this.begin = begin;
        return this;
    }

    public LocalTime getEnd() {
        return end;
    }

    public ClassesEntity setEnd(LocalTime end) {
        this.end = end;
        return this;
    }
}
