package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.security.RoleProtection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path(EmployeeService.PATH_PART)
public class EmployeeService {
    public static final String PATH_PART = "/employee";
    @Inject
    RoleProtection roleProtection;

    @POST
    @Path("/add")
    @Transactional
    public Response add(Employee emp, @Context UriInfo uriInfo) {
        roleProtection.asAdmin(() -> emp.persist());
        return AppResponse.created(PATH_PART, uriInfo, emp);
    }

    @POST
    @Path("/update")
    @Transactional
    public Response update(Employee emp) {
        roleProtection.asAdmin(emp::merge);
        return AppResponse.ok();
    }

    @GET
    @Path("/list")
    public List<Employee> list() {
        return roleProtection.asAdmin(() -> Employee.listAll());
    }
}
