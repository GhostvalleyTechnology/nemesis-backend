package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class ProofOfIdentity extends FileEntityBase {
  public ProofOfIdentityType type;

  public static ProofOfIdentity byId(long id) {
    Optional<ProofOfIdentity> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
