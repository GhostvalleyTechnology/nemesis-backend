package com.quellkunst.nemesis.security;

/**
 * Currently, as we do not support role authentication through auth0, this interface provides a
 * simple check if the current use has the needed authority.
 */
public interface Guard {

  /**
   * Checks if the current user has admin rights. If so, the action is executed. If not, an
   * exception is thrown
   *
   * @param action to execute
   * @param <T> the incoming command defines the output type
   * @return the type-safe action result
   */
  <T> T asAdmin(ReturnableRunnable<T> action);

  /**
   * Checks if the current user has admin rights. If so, the action is executed. If not, an
   * exception is thrown
   *
   * @param action to execute
   */
  void asAdmin(Runnable action);

  /**
   * Checks if the current user has admin rights.
   *
   * @return if the current user has admin rights.
   */
  boolean isAdmin();

  void asEmployee(EmployeeCheck check, Runnable action);

  <T> T asEmployee(EmployeeCheck check, ReturnableRunnable<T> action);
}
