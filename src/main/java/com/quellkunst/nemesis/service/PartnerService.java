package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.security.RoleProtection;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path(PartnerService.PATH_PART)
public class PartnerService {
  public static final String PATH_PART = "/partner";
  @Inject
  RoleProtection roleProtection;

  @POST
  @Path("/add")
  @Transactional
  public Response add(Partner partner, @Context UriInfo uriInfo) {
    roleProtection.asAdmin(() -> partner.persist());
    return AppResponse.created(PATH_PART, uriInfo, partner);
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(Partner partner) {
    roleProtection.asAdmin(partner::merge);
    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  public List<Partner> list() {
    return Partner.listAll();
  }
}
