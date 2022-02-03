package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.PartnerDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path(PartnerService.PATH_PART)
public class PartnerService {
  public static final String PATH_PART = "/partner";
  @Inject Guard guard;

  @POST
  @Path("/add")
  @Transactional
  public Response add(PartnerDto partner, @Context UriInfo uriInfo) {
    guard.asAdmin(partner::newEntity);
    return AppResponse.created(PATH_PART, uriInfo, partner);
  }

  @POST
  @Path("/update")
  @Transactional
  public Response update(PartnerDto partner) {
    guard.asAdmin(partner::updateEntity);
    return AppResponse.ok();
  }

  @GET
  @Path("/list")
  @Transactional
  public List<PartnerDto> list() {
    Stream<Partner> partnerStream = Partner.streamAll();
    return partnerStream.map(PartnerDto::of).map(this::filterLogins).collect(Collectors.toList());
  }

  @GET
  @Path("/get/{partnerId}")
  public PartnerDto get(@PathParam long partnerId) {
    return filterLogins(PartnerDto.of(Partner.byId(partnerId)));
  }

  private PartnerDto filterLogins(PartnerDto partner) {
    if (!guard.isAdmin()) {
      partner.setLogins(
          partner.getLogins().stream().filter(login -> !login.isAdminOnly()).collect(Collectors.toList()));
    }
    return partner;
  }
}
