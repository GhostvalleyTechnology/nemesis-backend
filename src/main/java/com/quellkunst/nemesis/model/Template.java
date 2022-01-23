package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class Template extends FileEntityBase {
  public boolean adminOnly;

  @Builder
  public Template(String fileName, Long fileId, boolean adminOnly) {
    super(fileName, fileId);
    this.adminOnly = adminOnly;
  }
}
