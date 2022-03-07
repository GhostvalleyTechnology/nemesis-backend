package com.quellkunst.nemesis.security;

import io.quarkus.security.UnauthorizedException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Currently, as we do not support role authentication through auth0, this interface provides a
 * simple check if the current use has the needed authority.
 */
@ApplicationScoped
public class Guard {

  private final UnauthorizedException INSUFFICIENT_RIGHTS =
      new UnauthorizedException("Insufficient Rights");

  @Inject AppContext context;

  /**
   * Checks if the current user has admin rights.
   *
   * @return if the current user has admin rights.
   */
  public boolean isAdmin() {
    return "admin@quellkunst.com".equals(context.getEmail()) || context.getCurrentEmployee().admin;
  }

  /**
   * Checks if the current user has admin rights. If so, the action is executed. If not, an
   * exception is thrown
   *
   * @param action to execute
   * @param <T> the incoming command defines the output type
   * @return the type-safe action result
   */
  public <T> T asAdmin(ReturnableRunnable<T> action) {
    if (isAdmin()) {
      return action.run();
    }
    throw INSUFFICIENT_RIGHTS;
  }

  /**
   * Checks if the current user has admin rights. If so, the action is executed. If not, an
   * exception is thrown
   *
   * @param action to execute
   */
  public void asAdmin(Runnable action) {
    asAdmin(
        () -> {
          action.run();
          return null;
        });
  }

  public void asEmployee(EmployeeCheck check, Runnable action) {
    asEmployee(
        check,
        () -> {
          action.run();
          return null;
        });
  }

  public <T> T asEmployee(EmployeeCheck check, ReturnableRunnable<T> action) {
    if (isAdmin() || context.getCurrentEmployee().equals(check.responsibleEmployee())) {
      return action.run();
    }
    throw INSUFFICIENT_RIGHTS;
  }
}
