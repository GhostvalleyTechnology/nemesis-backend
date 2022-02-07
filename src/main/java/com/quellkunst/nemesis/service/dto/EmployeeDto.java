package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Employee;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class EmployeeDto extends AbstractEntityDto<Employee> {
  String name;
  String email;
  boolean admin;

  protected EmployeeDto(Employee entity) {
    super(entity);
  }

  public static EmployeeDto of(Employee entity) {
    var dto = new EmployeeDto(entity);
    dto.name = entity.name;
    dto.email = entity.email;
    dto.admin = entity.admin;
    return dto;
  }

  @Override
  protected Employee prepareNewEntity() {
    return mapValues(new Employee());
  }

  @Override
  protected Employee prepareUpdateEntity() {
    return mapValues(Employee.byId(id));
  }

  private Employee mapValues(Employee entity) {
    entity.name = name;
    entity.email = email;
    entity.admin = admin;
    return entity;
  }
}
