package com.quellkunst.nemesis.repository;

import static com.quellkunst.nemesis.security.ExceptionSupplier.forbiddenException;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.model.Employee_;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository extends BaseRepository<Employee> {
  public Optional<Employee> findByEmail(String email) {
    return find(Employee_.EMAIL, email).firstResultOptional();
  }

  public Employee getByEmail(String email) {
    return findByEmail(email)
        .orElseThrow(forbiddenException("E-Mail '" + email + "' not registered."));
  }
}
