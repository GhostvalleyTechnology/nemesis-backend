package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.security.Context;
import com.quellkunst.nemesis.security.RoleProtected;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.annotations.cache.NoCache;

@Path("/client")
public class ClientService {

  @Inject
  Context context;
  @Inject
  RoleProtected roleProtected;

  @GET
  @NoCache
  @Path("/all")
  public List<Client> getAllClients() {
    AtomicReference<List<Client>> result = new AtomicReference<>(Collections.emptyList());
    roleProtected.asAdmin(() -> result.set(Client.listAll()));
    return result.get();
  }

  @GET
  @NoCache
  public List<Client> getClients() {
    // TODO
    return Client.list("from Client");
  }
}
