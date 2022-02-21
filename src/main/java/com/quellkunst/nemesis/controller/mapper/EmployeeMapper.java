package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.service.dto.EmployeeDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.spi.CDI;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;

@Mapper(config = QuarkusMappingConfig.class)
public interface EmployeeMapper {
  EmployeeDto toDto(Employee entity);

  Employee newEntity(EmployeeDto dto);

  void updateEntity(EmployeeDto dto, @MappingTarget Employee entity);

  @GetEmployeeByContext
  default Employee getEmployeeByContext(String ignoredSupervisor) {
    return CDI.current().select(AppContext.class).get().getCurrentEmployee();
  }

  @AfterMapping
  default void persist(@MappingTarget Employee entity) {
    entity.persist();
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetEmployeeByContext {}
}
