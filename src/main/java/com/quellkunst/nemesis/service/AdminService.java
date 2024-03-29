package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.security.Guard;
import io.quarkus.mailer.Mailer;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/admin")
@Transactional
public class AdminService {
  @Inject Guard guard;
  @Inject Mailer mailer;

  @POST
  @Path("/daily")
  public Response dailyTasks() {
    guard.asAdmin(() -> Reminder.getDueToday().forEach(this::sendReminderEmail));
    return Response.ok().build();
  }

  private void sendReminderEmail(Reminder reminder) {
    mailer.send(reminder.getMail());
    reminder.sent();
  }
}
