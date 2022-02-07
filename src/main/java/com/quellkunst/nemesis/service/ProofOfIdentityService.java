package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityDto;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityUploadDto;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/proof-of-identity")
@Transactional
public class ProofOfIdentityService {
  @Inject AppResponse appResponse;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/add")
  public ProofOfIdentityDto add(@MultipartForm ProofOfIdentityUploadDto input) {
    var googleFile = input.persist();
    var proofOfIdentity = ProofOfIdentity.builder().type(input.type).file(googleFile).build();
    proofOfIdentity.persist();
    Client client = Client.byId(input.clientId);
    client.proofOfIdentities.add(proofOfIdentity);
    return ProofOfIdentityDto.of(proofOfIdentity);
  }

  @GET
  @Path("/get/{id}")
  public Response get(@PathParam long id) {
    ProofOfIdentity contract = ProofOfIdentity.byId(id);
    return appResponse.fileDownload(contract.file);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ProofOfIdentity identity = ProofOfIdentity.byId(id);
    identity.removeFromClient();
    identity.delete();
    return appResponse.ok();
  }
}
