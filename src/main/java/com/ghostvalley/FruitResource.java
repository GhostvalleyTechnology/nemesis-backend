package com.ghostvalley;

import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.*;

@Path("/fruits")
public class FruitResource {

    @GET
    public List<Fruit> list() {
        return Fruit.listAll();
    }

    @GET
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
