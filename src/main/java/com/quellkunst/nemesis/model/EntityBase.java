package com.quellkunst.nemesis.model;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

import com.quellkunst.nemesis.Identifiable;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.enterprise.inject.spi.CDI;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase implements Identifiable {
  @Id @GeneratedValue public long id;

  @CreationTimestamp public LocalDateTime createdAt;

  public static <T extends EntityBase> T byId(long id) {
    Optional<T> maybe = T.findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }

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
