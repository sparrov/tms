package pl.szymonwrobel.tms.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "blocks")
public class BlockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public BlockEntity() {
    }

    public BlockEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
