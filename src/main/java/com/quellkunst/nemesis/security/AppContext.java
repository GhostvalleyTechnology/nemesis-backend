package com.quellkunst.nemesis.security;

import com.quellkunst.nemesis.model.Employee;

public interface AppContext {
  String getEmail();

  Employee getCurrentEmployee();
}
