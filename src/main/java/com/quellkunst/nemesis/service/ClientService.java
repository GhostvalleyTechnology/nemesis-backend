package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Client_;
import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.security.Guard;
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

@Path(ClientService.PATH_PART)
public class ClientService {
  public static final String PATH_PART = "/client";

  @Inject AppContext context;
  @Inject Guard guard;

  @GET
  @Path("/list-all")
  public List<Client> listAll() {
    return guard.asAdmin(() -> Client.listAll());
  }

  @POST
  @Path("/add")
  @Transactional
  public Response add(Client client, @Context UriInfo uriInfo) {
    client.supervisor = context.getCurrentEmployee();
    client.persist();
    Reminder.createNewClientReminders(client);
    return AppResponse.created(PATH_PART, uriInfo, client);
  }

  @GET
  @Path("/get/{id}")
  public Client get(@PathParam long id) {
    return Client.byId(id);
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(Client client) {
    client.merge();
    return AppResponse.ok();
  }

  @POST
  @Path("/delete")
  @Transactional
  public Response delete(Client client) {
    client.deleted = true;
    client.merge();
    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  public List<Client> list() {
    return Client.list(
        String.format(
            "from Client where %s = false and %s = ?1", Client_.DELETED, Client_.SUPERVISOR),
        context.getCurrentEmployee());
  }
}
