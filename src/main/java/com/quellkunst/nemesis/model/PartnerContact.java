package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Entity
public class PartnerContact extends EntityBase {
  @ManyToOne public Partner partner;
  public String name;
  public String email;
  public String phone;
  public String remarks;
}
