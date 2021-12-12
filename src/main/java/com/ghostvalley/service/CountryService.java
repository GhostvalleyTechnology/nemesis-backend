package com.ghostvalley.service;

import com.ghostvalley.model.Country;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/country")
public class CountryService {

    @GET
    public List<Country> list() {
        return Country.listAll();
    }

    @POST
    @Transactional
    @Path("/add")
    public void add(Country country) {
        Country.persist(country);
    }
}
