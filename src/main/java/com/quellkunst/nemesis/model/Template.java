package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Template extends EntityBase {
  public boolean adminOnly;
  @Embedded public GoogleFile file;
}