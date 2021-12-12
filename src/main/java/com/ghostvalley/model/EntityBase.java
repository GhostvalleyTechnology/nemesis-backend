package com.ghostvalley.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase {
    @Id
    @GeneratedValue
    private Long id;
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + id + ">";
    }
}
