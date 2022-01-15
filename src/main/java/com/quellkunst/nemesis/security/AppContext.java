package com.quellkunst.nemesis.security;

import com.quellkunst.nemesis.model.Employee;
import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.unauthorizedException;

@ApplicationScoped
public class AppContext implements Context {
  @Inject @IdToken Instance<JsonWebToken> idToken;

  @Override
  public String getEmail() {
    Optional<String> jwtEmail = idToken.get().claim(Claims.email);
    return jwtEmail.orElseThrow(unauthorizedException("E-Mail Address is not configured!"));
  }

  @Override
  public Employee getCurrentEmployee() {
    return Employee.getByEmail(getEmail());
  }
}
