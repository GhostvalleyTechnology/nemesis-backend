package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.security.RoleProtection;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/client")
public class ClientService {

  @Inject
  AppContext context;
  @Inject
  RoleProtection roleProtection;

  @GET
  @NoCache
  @Path("/list-all")
  public List<Client> getAllClients() {
    return roleProtection.asAdmin(() -> Client.listAll());
  }

  @POST
  @Path("/add")
  @Transactional
  public Response add(Client client) {
    client.supervisor = context.getCurrentEmployee();
    client.persist();
    Reminder.createNewClientReminders(client);
    return Response.status(Response.Status.CREATED).build();
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(Client client) {
    client.merge();
    return Response.ok().build();
  }

  @POST
  @Path("/delete")
  @Transactional
  public Response delete(Client client) {
    client.deleted = true;
    client.merge();
    return Response.ok().build();
  }

  @GET
  @NoCache
  @Path("/list")
  public List<Client> getClients() {
    return Client.list(
        "from Client where deleted = false and supervisor = ?1", context.getCurrentEmployee());
  }
}
