package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class PartnerLogin extends EntityBase {
  public String link;
  public String username;
  public String password;
  public boolean adminOnly;

  public static PartnerLogin byId(long id) {
    Optional<PartnerLogin> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
