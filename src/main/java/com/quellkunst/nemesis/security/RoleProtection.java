package com.quellkunst.nemesis.security;

import com.quellkunst.nemesis.model.Employee;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RoleProtection implements RoleProtected {

  private final IllegalCallerException INSUFFICIENT_RIGHTS = new IllegalCallerException(
      "Insufficient Rights");

  @Inject
  Context context;

  @Override
  public void asAdmin(Runnable action) {
    var employee = Employee.getByEmail(context.getEmail());
    if (employee.isAdminRights()) {
      action.run();
    } else {
      throw INSUFFICIENT_RIGHTS;
    }
  }

}
