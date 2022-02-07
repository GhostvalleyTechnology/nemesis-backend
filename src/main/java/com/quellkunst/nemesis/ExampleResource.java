package com.quellkunst.nemesis;

import com.quellkunst.nemesis.security.AppContext;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {
  @Inject AppContext context;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return context.getEmail();
  }

  @Inject Mailer mailer;

  @GET
  @Blocking
  @Path("/mail")
  public void sendEmail() {
    mailer.send(
        Mail.withText(
            "schmid.kevin.manuel@gmail.com",
            "Ahoy from Quarkus",
            "A simple email sent from a Quarkus application."));
  }
}
