package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.RoleProtection;
import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/admin")
public class AdminService {
  @Inject
  RoleProtection roleProtection;
  @Inject Mailer mailer;

  @POST
  @Path("/daily")
  @Transactional
  public Response dailyTasks() {
    roleProtection.asAdmin(() -> Reminder.getDueToday().forEach(this::sendReminderEmail));
    return Response.ok().build();
  }

  private void sendReminderEmail(Reminder reminder) {
    mailer.send(reminder.getMail());
    reminder.sent();
  }
}
