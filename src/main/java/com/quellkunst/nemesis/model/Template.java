package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class Template extends FileEntityBase {
  public boolean adminOnly;

  @Builder
  public Template(String fileName, Long fileId, boolean adminOnly) {
    super(fileName, fileId);
    this.adminOnly = adminOnly;
  }

  public static Template byId(long id) {
    Optional<Template> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
