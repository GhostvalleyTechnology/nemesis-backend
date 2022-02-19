package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.ClientController;
import com.quellkunst.nemesis.controller.mapper.ClientMapper;
import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Client_;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.ClientDto;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Path(ClientService.PATH_PART)
public class ClientService {
  public static final String PATH_PART = "/client";

  @Inject AppContext context;
  @Inject AppResponse appResponse;
  @Inject Guard guard;
  @Inject ClientRepository repository;
  @Inject ClientController controller;
  @Inject ClientMapper mapper;

  @GET
  @Path("/list-all")
  public List<ClientDto> listAll() {
    return guard.asAdmin(
        () -> {
          Stream<Client> stream = Client.streamAll();
          return stream.map(mapper::toDto).collect(Collectors.toList());
        });
  }

  @POST
  @Path("/add")
  @APIResponse(
      responseCode = "201",
      headers = {@Header(name = "location")})
  public Response add(ClientDto dto, @Context UriInfo uriInfo) {
    var client = controller.add(dto);
    return appResponse.created(PATH_PART, uriInfo, client);
  }

  @GET
  @Path("/get/{id}")
  public ClientDto get(@PathParam long id) {
    var client = repository.byId(id);
    return mapper.toDto(client);
  }

  @POST
  @Path("/update")
  public Response update(ClientDto dto) {
    controller.update(dto);
    return appResponse.ok();
  }

  @DELETE
  @Path("/delete/{clientId}")
  public Response delete(@PathParam long clientId) {
    var client = repository.byId(clientId);
    guard.asAdmin(() -> controller.delete(client));
    return appResponse.ok();
  }

  @GET
  @Path("/list")
  public List<ClientDto> list() {
    return repository.stream(
            String.format(
                "from Client where %s = false and %s = ?1", Client_.DELETED, Client_.SUPERVISOR),
            context.getCurrentEmployee())
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }
}
