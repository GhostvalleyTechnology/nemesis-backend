package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class PartnerServiceType extends EntityBase implements Comparable<PartnerServiceType> {
  @Column(unique = true)
  public String service;

  @Builder
  public PartnerServiceType(String service) {
    this.service = service;
  }

  public static PartnerServiceType byId(long id) {
    Optional<PartnerServiceType> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }

  @Override
  public int compareTo(PartnerServiceType that) {
    return this.service.compareTo(that.service);
  }
}
