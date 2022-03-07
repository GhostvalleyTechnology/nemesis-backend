package com.quellkunst.nemesis.repository;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

import com.quellkunst.nemesis.model.EntityBase_;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.Optional;

public abstract class BaseRepository<Entity> implements PanacheRepository<Entity> {
  public Entity byId(long id) {
    Optional<Entity> maybe = find(EntityBase_.ID, id).firstResultOptional();
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
