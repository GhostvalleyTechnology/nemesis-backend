package com.quellkunst.nemesis.security;

import io.quarkus.security.UnauthorizedException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class GuardImpl implements Guard {

  private final UnauthorizedException INSUFFICIENT_RIGHTS =
      new UnauthorizedException("Insufficient Rights");

  @Inject AppContext context;

  @Override
  public boolean isAdmin() {
    return "admin@quellkunst.com".equals(context.getEmail()) || context.getCurrentEmployee().admin;
  }

  @Override
  public <T> T asAdmin(ReturnableRunnable<T> action) {
    if (isAdmin()) {
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

  @Override
  public void asEmployee(EmployeeCheck check, Runnable action) {
    asEmployee(
        check,
        () -> {
          action.run();
          return null;
        });
  }

  @Override
  public <T> T asEmployee(EmployeeCheck check, ReturnableRunnable<T> action) {
    if (isAdmin() || context.getCurrentEmployee().equals(check.responsibleEmployee())) {
      return action.run();
    }
    throw INSUFFICIENT_RIGHTS;
  }
}
