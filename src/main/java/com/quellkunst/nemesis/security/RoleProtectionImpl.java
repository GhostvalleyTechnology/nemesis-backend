package com.quellkunst.nemesis.security;

import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RoleProtectionImpl implements RoleProtection {

  private final UnauthorizedException INSUFFICIENT_RIGHTS =
      new UnauthorizedException("Insufficient Rights");

  @Inject AppContext context;

  @Override
  public <T> T asAdmin(AdminCommand<T> action) {
    if ("admin@quellkunst.com".equals(context.getEmail()) || context.getCurrentEmployee().admin) {
      return action.run();
    }
    throw INSUFFICIENT_RIGHTS;
  }

  @Override
  public void asAdmin(Runnable action) {
    asAdmin(
        () -> {
          action.run();
          return null;
        });
  }
}
