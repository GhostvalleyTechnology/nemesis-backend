package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class EmployeeDto extends AbstractEntityDto {
  String name;
  String email;
  boolean admin;
}
