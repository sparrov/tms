package pl.szymonwrobel.tms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BlockEntity {

    @Id
    private Long id;
    private String name;

    public BlockEntity() {
    }

    public BlockEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
