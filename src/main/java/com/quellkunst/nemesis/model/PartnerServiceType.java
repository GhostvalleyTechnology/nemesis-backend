package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PartnerServiceType extends EntityBase implements Comparable<PartnerServiceType> {
  @Column(unique = true)
  public String service;

  @Override
  public int compareTo(PartnerServiceType that) {
    return this.service.compareTo(that.service);
  }
}