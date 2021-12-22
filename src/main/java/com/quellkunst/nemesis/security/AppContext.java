package com.quellkunst.nemesis.security;

import static com.quellkunst.nemesis.security.ExceptionSupplier.theException;

import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class AppContext implements Context {

  private final IllegalCallerException EMAIL_NOT_CONFIGURED = new IllegalCallerException(
      "E-Mail Address is not configured.");

  @Inject
  JsonWebToken idToken;

  @Override
  public String getEmail() {
    Optional<String> jwtEmail = idToken.claim(Claims.email);
    return jwtEmail.orElseThrow(theException(EMAIL_NOT_CONFIGURED));
  }
}
