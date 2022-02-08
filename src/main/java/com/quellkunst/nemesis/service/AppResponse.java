package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.Identifiable;
import com.quellkunst.nemesis.model.CloudFile;
import com.quellkunst.nemesis.security.ExceptionSupplier;
import com.quellkunst.nemesis.security.GoogleStorage;
import io.quarkus.logging.Log;
import java.net.URI;
import java.net.URISyntaxException;
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
    try {
      return Response.status(Response.Status.FOUND).contentLocation(url.toURI()).build();
    } catch (URISyntaxException e) {
      Log.error(e);
      throw ExceptionSupplier.serviceUnavailableException("Download not available.").get();
    }
  }
}
