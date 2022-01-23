package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.security.Guard;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path(PartnerService.PATH_PART)
public class PartnerService {
  public static final String PATH_PART = "/partner";
  @Inject Guard guard;

  @POST
  @Path("/add")
  @Transactional
  public Response add(Partner partner, @Context UriInfo uriInfo) {
    guard.asAdmin(() -> partner.persist());
    return AppResponse.created(PATH_PART, uriInfo, partner);
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(Partner partner) {
    guard.asAdmin(partner::merge);
    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  public List<Partner> list() {
    List<Partner> partner = Partner.listAll();
    var result = new ArrayList<Partner>();
    partner.forEach(
        p -> {
          p.detach();
          if (!guard.isAdmin()) {
            p.logins =
                p.logins.stream().filter(login -> !login.adminOnly).collect(Collectors.toList());
          }
          result.add(p);
        });
    return result;
  }
}
