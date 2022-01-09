package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.security.Context;
import com.quellkunst.nemesis.security.RoleProtected;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

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
        return roleProtected.asAdmin(Client::listAll);
    }

    @GET
    @NoCache
    public List<Client> getClients() {
        return Client.list("from Client where supervisor = ?1", context.getUser().getEmail());
    }
}
