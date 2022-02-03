package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.EmployeeDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path(EmployeeService.PATH_PART)
public class EmployeeService {
  public static final String PATH_PART = "/employee";
  @Inject Guard guard;

  @POST
  @Path("/add")
  @Transactional
  public Response add(EmployeeDto emp, @Context UriInfo uriInfo) {
    guard.asAdmin(emp::newEntity);
    return AppResponse.created(PATH_PART, uriInfo, emp);
  }

  @GET
  @Path("/get/{id}")
  public EmployeeDto get(@PathParam long id) {
    return guard.asAdmin(() -> EmployeeDto.of(Employee.byId(id)));
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(EmployeeDto emp) {
    guard.asAdmin(emp::updateEntity);
    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  public List<EmployeeDto> list() {
    return guard.asAdmin(
        () -> {
          Stream<Employee> stream = Employee.streamAll();
          return stream.map(EmployeeDto::of).collect(Collectors.toList());
        });
  }
}
