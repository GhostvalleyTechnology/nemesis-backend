package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.security.RoleProtected;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/partner")
public class PartnerService {
  @Inject RoleProtected roleProtected;

  @POST
  @Path("/add")
  @Transactional
  public Response addPartner(Partner partner) {
    roleProtected.asAdmin(() -> partner.persist());
    return Response.ok().build();
  }
}
