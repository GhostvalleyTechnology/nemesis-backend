package com.quellkunst.nemesis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
