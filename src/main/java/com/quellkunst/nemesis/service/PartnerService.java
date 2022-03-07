package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.PartnerController;
import com.quellkunst.nemesis.controller.mapper.PartnerMapper;
import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.repository.PartnerRepository;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.PartnerDto;
import com.quellkunst.nemesis.service.dto.PartnerReferenceDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Transactional
@Path(PartnerService.PATH_PART)
public class PartnerService {
  public static final String PATH_PART = "/partner";
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject PartnerRepository repository;
  @Inject PartnerController controller;
  @Inject PartnerMapper mapper;

  @POST
  @Path("/add")
  public Response add(PartnerDto partner, @Context UriInfo uriInfo) {
    var entity = guard.asAdmin(() -> controller.add(partner));
    return appResponse.created(PATH_PART, uriInfo, entity);
  }

  @POST
  @Path("/update")
  public Response update(PartnerDto partner) {
    guard.asAdmin(() -> controller.update(partner));
    return appResponse.ok();
  }

  @GET
  @Path("/list")
  public List<PartnerReferenceDto> list() {
    Stream<Partner> partnerStream = Partner.streamAll();
    return partnerStream.map(mapper::toReferenceDto).collect(Collectors.toList());
  }

  @GET
  @Path("/get/{partnerId}")
  public PartnerDto get(@PathParam long partnerId) {
    return filterLogins(mapper.toDto(repository.byId(partnerId)));
  }

  private PartnerDto filterLogins(PartnerDto partner) {
    if (!guard.isAdmin()) {
      partner.setLogins(
          partner.getLogins().stream()
              .filter(login -> !login.isAdminOnly())
              .collect(Collectors.toList()));
    }
    return partner;
  }
}
