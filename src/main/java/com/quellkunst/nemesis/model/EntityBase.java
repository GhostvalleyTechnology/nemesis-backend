package com.quellkunst.nemesis.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.enterprise.inject.spi.CDI;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase {
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
}
