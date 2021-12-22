package com.quellkunst.nemesis.security;

import static com.quellkunst.nemesis.security.ExceptionSupplier.theException;

import com.quellkunst.nemesis.model.Employee;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class RoleProtection implements RoleProtected {

  private final IllegalCallerException INSUFFICIENT_RIGHTS = new IllegalCallerException(
      "Insufficient Rights");
  private final IllegalCallerException EMAIL_NOT_CONFIGURED = new IllegalCallerException(
      "E-Mail Address is not configured.");

  @Inject
  JsonWebToken idToken;

  @Override
  public void asAdmin(Runnable action) {
    var email = getIdTokenEmail();
    var employee = Employee.getByEmail(email);
    if (employee.isAdminRights()) {
      action.run();
    } else {
      throw INSUFFICIENT_RIGHTS;
    }
  }

  private String getIdTokenEmail() {
    Optional<String> jwtEmail = idToken.claim(Claims.email);
    return jwtEmail.orElseThrow(theException(EMAIL_NOT_CONFIGURED));
  }

}
