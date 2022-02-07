package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.Identifiable;
import com.quellkunst.nemesis.service.dto.FileDto;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class AppResponse {
  private AppResponse() {}

  public static Response ok() {
    return Response.ok().build();
  }

  public static Response deleted(boolean deleted) {
    return Response.status(deleted ? Response.Status.OK : Response.Status.NOT_FOUND).build();
  }

  public static Response created(String pathPart, UriInfo uriInfo, Identifiable identifiable) {
    var baseUri = uriInfo.getBaseUri().toString();
    // substring 5: length -1 - "/api"
    var location =
        baseUri.substring(0, baseUri.length() - 5) + pathPart + "/" + identifiable.getId();
    return Response.created(URI.create(location)).build();
  }

  public static FileDto fileDownload(FileEntityBase entityBase) {
    // var base64Encoded = Base64.getEncoder().encodeToString(file.data);
    return FileDto.of(File.byId(entityBase.fileId));
  }
}