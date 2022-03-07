package com.quellkunst.nemesis.model;

import com.quellkunst.nemesis.Identifiable;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@MappedSuperclass
@NoArgsConstructor
public abstract class EntityBase extends PanacheEntityBase implements Identifiable {
  @Id @GeneratedValue public long id;

  @CreationTimestamp public LocalDateTime createdAt;

  @Override
  public String toString() {
    return this.getClass().getSimpleName() + "<" + id + ">";
  }

  @Override
  public long getId() {
    return id;
  }
}
