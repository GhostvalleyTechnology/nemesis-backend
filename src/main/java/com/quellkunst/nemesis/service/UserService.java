package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.security.AppContext;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/me")
public class UserService {

  @Inject
  AppContext context;

  @GET
  public Employee get() {
    return context.getCurrentEmployee();
  }
}
