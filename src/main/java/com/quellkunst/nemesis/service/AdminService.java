package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.RoleProtected;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/admin")
public class AdminService {
    @Inject
    RoleProtected roleProtected;
    @Inject
    Mailer mailer;

    @POST
    @Path("/add-employee")
    @Transactional
    public Response addEmployee(Employee emp) {
        roleProtected.asSuperAdmin(() ->
                emp.persist()
        );
        return Response.ok().build();
    }

    @POST
    @Path("/list-employees")
    public List<Employee> getEmployees() {
        return roleProtected.asSuperAdmin(() ->
                Employee.listAll()
        );
    }

    @POST
    @Path("/daily")
    @Transactional
    public Response dailyTasks() {
        roleProtected.asSuperAdmin(() ->
                Reminder.getDueToday().forEach(this::sendReminderEmail)
        );
        return Response.ok().build();
    }

    private void sendReminderEmail(Reminder reminder) {
        mailer.send(reminder.getMail());
        reminder.sent();
    }
}
