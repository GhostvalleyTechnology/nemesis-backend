package com.quellkunst.nemesis.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Template extends EntityBase {
  public boolean adminOnly;
  @Embedded public CloudFile file;
}
