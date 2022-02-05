package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Client_;
import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.ClientDto;
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
  @Inject Guard guard;

  @GET
  @Path("/list-all")
  public List<ClientDto> listAll() {
    return guard.asAdmin(
        () -> {
          Stream<Client> stream = Client.streamAll();
          return stream.map(ClientDto::of).collect(Collectors.toList());
        });
  }

  @POST
  @Path("/add")
  public Response add(ClientDto dto, @Context UriInfo uriInfo) {
    Client client = dto.getEntity();
    client.supervisor = context.getCurrentEmployee();
    client.persist();
    Reminder.createNewClientReminders(client);
    return AppResponse.created(PATH_PART, uriInfo, client);
  }

  @GET
  @Path("/get/{id}")
  public ClientDto get(@PathParam long id) {
    return ClientDto.of(Client.byId(id));
  }

  @POST
  @Path("/update")
  public Response update(ClientDto client) {
    client.updateEntity();
    return AppResponse.ok();
  }

  @DELETE
  @Path("/delete/{clientId}")
  public Response delete(@PathParam long clientId) {
    guard.asAdmin(
        () -> {
          var client = Client.byId(clientId);
          client.deleted = true;
          client.persist();
        });

    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  public List<ClientDto> list() {
    Stream<Client> stream =
        Client.stream(
            String.format(
                "from Client where %s = false and %s = ?1", Client_.DELETED, Client_.SUPERVISOR),
            context.getCurrentEmployee());
    return stream.map(ClientDto::of).collect(Collectors.toList());
  }
}
