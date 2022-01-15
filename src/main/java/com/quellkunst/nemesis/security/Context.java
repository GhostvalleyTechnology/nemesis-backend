package com.quellkunst.nemesis.security;

import com.quellkunst.nemesis.model.Employee;

public interface Context {
  String getEmail();

  Employee getCurrentEmployee();
}
