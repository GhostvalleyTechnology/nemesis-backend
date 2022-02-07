package com.quellkunst.nemesis.security;

import static com.quellkunst.nemesis.security.ExceptionSupplier.unauthorizedException;

import com.quellkunst.nemesis.model.Employee;
import io.quarkus.oidc.IdToken;
import io.quarkus.runtime.LaunchMode;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class AppContext {
  @Inject @IdToken Instance<JsonWebToken> idToken;

  public String getEmail() {
    if (LaunchMode.current().isDevOrTest()) {
      return "admin@quellkunst.com";
    }
    Optional<String> jwtEmail = idToken.get().claim(Claims.email);
    return jwtEmail.orElseThrow(unauthorizedException("E-Mail Address is not configured!"));
  }

  public Employee getCurrentEmployee() {
    return Employee.getByEmail(getEmail());
  }
}
