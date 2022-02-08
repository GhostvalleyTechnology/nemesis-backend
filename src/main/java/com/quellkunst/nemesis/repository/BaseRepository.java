package com.quellkunst.nemesis.repository;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.Optional;

public abstract class BaseRepository<Entity> implements PanacheRepository<Entity> {
  public Entity byId(long id) {
    Optional<Entity> maybe = find("id", id).firstResultOptional();
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
