package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.mapper.EmployeeMapper;
import com.quellkunst.nemesis.repository.EmployeeRepository;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.EmployeeDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Transactional
@Path(EmployeeService.PATH_PART)
public class EmployeeService {
  public static final String PATH_PART = "/employee";
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject EmployeeRepository repository;
  @Inject EmployeeMapper mapper;

  @POST
  @Path("/add")
  public Response add(EmployeeDto dto, @Context UriInfo uriInfo) {
    var entity = guard.asAdmin(() -> mapper.newEntity(dto));
    return appResponse.created(PATH_PART, uriInfo, entity);
  }

  @GET
  @Path("/get/{id}")
  public EmployeeDto get(@PathParam long id) {
    return guard.asAdmin(() -> mapper.toDto(repository.byId(id)));
  }

  @POST
  @Path("/update")
  public Response update(EmployeeDto dto) {
    guard.asAdmin(() -> mapper.updateEntity(dto, repository.byId(dto.getId())));
    return appResponse.ok();
  }

  @GET
  @Path("/list")
  public List<EmployeeDto> list() {
    return guard.asAdmin(
        () -> repository.streamAll().map(mapper::toDto).collect(Collectors.toList()));
  }
}
