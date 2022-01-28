package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class PartnerServiceType extends EntityBase implements Comparable<PartnerServiceType> {
  @Column(unique = true)
  String service;

  @Builder
  public PartnerServiceType(String service) {
    this.service = service;
  }

  @Override
  public int compareTo(PartnerServiceType that) {
    return this.service.compareTo(that.service);
  }
}
