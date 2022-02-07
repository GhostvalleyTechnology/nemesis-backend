package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.forbiddenException;
import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee extends EntityBase {
  public String name;

  @Column(unique = true)
  public String email;
  public boolean admin;

  public static Optional<Employee> findByEmail(String email) {
    return find(Employee_.EMAIL, email).firstResultOptional();
  }

  public static Employee getByEmail(String email) {
    return findByEmail(email)
        .orElseThrow(forbiddenException("E-Mail '" + email + "' not registered."));
  }
}