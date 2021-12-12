package com.ghostvalley.service;

import com.ghostvalley.model.Client;
import com.ghostvalley.model.Country;
import com.ghostvalley.model.Employee;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/dev")
public class DevService {

    @GET
    @Transactional
    public void init() {
        Country.persist(Country.builder().name("Austria").alpha2("AT").build());
        var emp = Employee.builder().email("test").build();
        Employee.persist(emp);
        Client.persist(Client.builder().supervisor(emp).firstName("A").build());
        Client.persist(Client.builder().firstName("B").supervisor(emp).build());
    }

    @GET
    @Path("clients")
    public List<Client> getClients() {
        return Client.listAll();
    }
}
