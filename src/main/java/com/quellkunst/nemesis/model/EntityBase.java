package com.quellkunst.nemesis.model;

import com.quellkunst.nemesis.Identifiable;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase implements Identifiable {
  @Id @GeneratedValue public long id;

  @CreationTimestamp public LocalDateTime createdAt;

  @Transient
  public void merge() {
    CDI.current().select(EntityManager.class).get().merge(this);
  }

  @Transient
  public void detach() {
    CDI.current().select(EntityManager.class).get().detach(this);
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "<" + id + ">";
  }

  @Override
  public long getId() {
    return id;
  }
}
