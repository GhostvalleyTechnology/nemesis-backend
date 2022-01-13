package com.quellkunst.nemesis.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public long id;

    @CreationTimestamp
    public LocalDateTime createdAt;

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + id + ">";
    }
}
