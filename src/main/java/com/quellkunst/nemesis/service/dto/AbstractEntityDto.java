package com.quellkunst.nemesis.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quellkunst.nemesis.Identifiable;
import com.quellkunst.nemesis.model.EntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@RegisterForReflection
public abstract class AbstractEntityDto<T extends EntityBase> implements Identifiable {
  long id;
  @Getter LocalDateTime createdAt;

  protected AbstractEntityDto(T entity) {
    this.id = entity.id;
    this.createdAt = entity.createdAt;
  }

  @Override
  public long getId() {
    return id;
  }

  protected abstract T prepareNewEntity();

  protected abstract T prepareUpdateEntity();

  @JsonIgnore
  public abstract T getEntity();

  @JsonIgnore
  protected T createOrUpdateEntity() {
    if (id > 0) {
      return updateEntity();
    }
    return newEntity();
  }

  public T newEntity() {
    var entity = prepareNewEntity();
    entity.persist();
    id = entity.id;
    return entity;
  }

  public T updateEntity() {
    var entity = prepareUpdateEntity();
    entity.persist();
    return entity;
  }
}
