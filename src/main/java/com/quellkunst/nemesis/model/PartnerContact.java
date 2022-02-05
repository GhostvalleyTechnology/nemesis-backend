package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class PartnerContact extends EntityBase {
  @ManyToOne public Partner partner;
  public String name;
  public String email;
  public String phone;
  public String remarks;

  public static PartnerContact byId(long id) {
    Optional<PartnerContact> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
