package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.Identifiable;
import com.quellkunst.nemesis.model.CloudFile;
import com.quellkunst.nemesis.security.GoogleStorage;
import java.net.URI;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@ApplicationScoped
public class AppResponse {
  @Inject GoogleStorage googleStorage;

  public Response ok() {
    return Response.ok().build();
  }

  public Response deleted(boolean deleted) {
    return Response.status(deleted ? Response.Status.OK : Response.Status.NOT_FOUND).build();
  }

  public Response created(String pathPart, UriInfo uriInfo, Identifiable identifiable) {
    var baseUri = uriInfo.getBaseUri().toString();
    // substring 5: length -1 - "/api"
    var location =
        baseUri.substring(0, baseUri.length() - 5) + pathPart + "/" + identifiable.getId();
    return Response.created(URI.create(location)).build();
  }

  public Response fileDownload(CloudFile cloudFile) {
    var url = googleStorage.download(cloudFile);
    return Response.status(Response.Status.OK).entity(url).build();
  }
}
