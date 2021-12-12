package com.ghostvalley.service;

import com.ghostvalley.Env;
import com.ghostvalley.model.Client;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/client")
public class ClientService {
    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed({Env.Roles.ADMIN})
    @Path("/admin")
    public String admin() {
        return "Admin Success!";
    }

    @GET
    @RolesAllowed({Env.Roles.USER})
    @Path("/user")
    public String user() {
        return "User Success!";
    }

    @GET
    @Path("/open")
    public String open() {
        return "Open Success!";
    }

    @GET
    @Path("/me")
    @RolesAllowed("user")
    @NoCache
    public User me() {
        return new User(securityIdentity);
    }

    public static class User {

        private final String userName;

        User(SecurityIdentity securityIdentity) {
            this.userName = securityIdentity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }

    @GET
    @RolesAllowed({Env.Roles.ADMIN, Env.Roles.USER})
    @NoCache
    @Path("/all")
    public List<Client> getClients() {
        if (securityIdentity.getRoles().contains(Env.Roles.ADMIN)) {
            return Client.listAll();
        }
        return Client.findById(1);
    }
}
