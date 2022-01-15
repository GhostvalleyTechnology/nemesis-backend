package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.forbiddenException;

@Entity
@NoArgsConstructor
public class Employee extends EntityBase {
  public String name;

  @Column(unique = true)
  public String email;

  public boolean admin;

  @Builder
  public Employee(String name, String email, boolean admin) {
    this.name = name;
    this.email = email;
    this.admin = admin;
  }

  public static Optional<Employee> findByEmail(String email) {
    return find(Employee_.EMAIL, email).firstResultOptional();
  }

  public static Employee getByEmail(String email) {
    return findByEmail(email)
        .orElseThrow(forbiddenException("E-Mail '" + email + "' not registered."));
  }
}
