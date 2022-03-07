package com.quellkunst.nemesis.model;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartnerLogin extends EntityBase {
  public String link;
  public String username;
  public String password;
  public boolean adminOnly;
}
