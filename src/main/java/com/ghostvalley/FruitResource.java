package com.ghostvalley;

import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.annotations.cache.NoCache;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;

@Path("/fruits")
public class FruitResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed("user")
    @NoCache
    public List<Fruit> list() {
        return Fruit.listAll();
    }

    @GET
    @RolesAllowed("admin")
    @Path("/{id}")
    public Fruit get(@PathParam("id") Long id) {
        return Fruit.findById(id);
    }

    @POST
    @Transactional
    public Fruit add(Fruit fruit) {
        fruit.persist();
        return fruit;
    }

    @DELETE
    public void delete(Fruit fruit) {
        Fruit.deleteById(fruit.id);
    }
}
