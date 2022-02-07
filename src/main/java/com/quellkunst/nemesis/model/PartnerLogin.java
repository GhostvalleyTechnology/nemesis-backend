package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PartnerLogin extends EntityBase {
  public String link;
  public String username;
  public String password;
  public boolean adminOnly;

}