package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.PartnerServiceType;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.PartnerServiceTypeDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@Path(PartnerServiceTypeService.PATH_PART)
public class PartnerServiceTypeService {
  public static final String PATH_PART = "/partner-service-type";
  @Inject Guard guard;

  @POST
  @Path("/add")
  public Response add(PartnerServiceTypeDto service, @Context UriInfo uriInfo) {
    guard.asAdmin(service::newEntity);
    return AppResponse.created(PATH_PART, uriInfo, service);
  }

  @GET
  @Path("/list")
  public List<PartnerServiceTypeDto> list() {
    Stream<PartnerServiceType> stream = PartnerServiceType.streamAll();
    return guard.asAdmin(() -> stream.map(PartnerServiceTypeDto::of).collect(Collectors.toList()));
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    return AppResponse.deleted(guard.asAdmin(() -> PartnerServiceType.deleteById(id)));
  }
}
