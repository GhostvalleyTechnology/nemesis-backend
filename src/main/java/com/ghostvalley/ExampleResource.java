package com.ghostvalley;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.oidc.IdToken;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {
    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String hello() {
        return
                "<html>\n" +
                        "    <body>\n" +
                        "        <h1>Hello " + idToken.getClaim("email") + "</h1>\n" +
                        "    </body>\n" +
                        "</html>\n";
    }

    @Inject
    Mailer mailer;

    @GET
    @Blocking
    @Path("/mail")
    public void sendEmail() {
        mailer.send(
                Mail.withText("schmid.kevin.manuel@gmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application."
                )
        );
    }

}