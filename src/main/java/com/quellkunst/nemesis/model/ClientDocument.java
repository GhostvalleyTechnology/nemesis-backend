package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class ClientDocument extends FileEntityBase {
  @ManyToOne public Client client;

  public static ClientDocument byId(long id) {
    Optional<ClientDocument> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
