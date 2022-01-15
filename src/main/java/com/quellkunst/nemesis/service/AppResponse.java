package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.EntityBase;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class AppResponse {
    private AppResponse(){}
    public static Response ok() {
        return Response.ok().build();
    }
    public static Response created(String pathPart, UriInfo uriInfo, EntityBase entity) {
        var baseUri = uriInfo.getBaseUri().toString();
        // substring 5: length -1 - "/api"
        var location = baseUri.substring(0, baseUri.length()-5)+pathPart+"/"+entity.id;
        return Response.created(URI.create(location)).build();
    }
}
