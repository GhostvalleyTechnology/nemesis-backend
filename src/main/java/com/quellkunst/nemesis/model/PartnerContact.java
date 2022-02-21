package com.quellkunst.nemesis.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartnerContact extends EntityBase {
  @ManyToOne public Partner partner;
  public String name;
  public String email;
  public String phone;
  public String remarks;
}
