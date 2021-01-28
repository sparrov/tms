package pl.szymonwrobel.tms.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "blocks")
public class BlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false, length = 60)
    private String name;

    @OneToMany
    @JoinColumn(name = "classes")
    private Set<ClassesEntity> classes = new HashSet<>();

    public BlockEntity() {
    }

    public BlockEntity(Long id, @NotEmpty String name, Set<ClassesEntity> classes) {
        this.id = id;
        this.name = name;
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public BlockEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BlockEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Set<ClassesEntity> getClasses() {
        return classes;
    }

    public BlockEntity setClasses(Set<ClassesEntity> classes) {
        this.classes = classes;
        return this;
    }
}
