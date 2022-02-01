package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.EntityBase;
import com.quellkunst.nemesis.model.File;
import com.quellkunst.nemesis.model.FileEntityBase;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Base64;

public class AppResponse {
  private AppResponse() {}

  public static Response ok() {
    return Response.ok().build();
  }

  public static Response created(String pathPart, UriInfo uriInfo, EntityBase entity) {
    var baseUri = uriInfo.getBaseUri().toString();
    // substring 5: length -1 - "/api"
    var location = baseUri.substring(0, baseUri.length() - 5) + pathPart + "/" + entity.id;
    return Response.created(URI.create(location)).build();
  }

  public static Response fileDownload(FileEntityBase entityBase) {
    File file = File.byId(entityBase.fileId);
    var base64Encoded = Base64.getEncoder().encodeToString(file.data);
    return Response.ok(base64Encoded, MediaType.APPLICATION_OCTET_STREAM).build();
  }
}
