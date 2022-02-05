package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.service.dto.FileDto;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityDto;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityUploadDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/proof-of-identity")
@Transactional
public class ProofOfIdentityService {

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/add")
  public ProofOfIdentityDto add(@MultipartForm ProofOfIdentityUploadDto input) {
    var fileId = input.persist();
    var proofOfIdentity =
        ProofOfIdentity.builder().type(input.type).fileId(fileId).fileName(input.fileName).build();
    proofOfIdentity.persist();
    Client client = Client.byId(input.clientId);
    client.proofOfIdentities.add(proofOfIdentity);
    return ProofOfIdentityDto.of(proofOfIdentity);
  }

  @GET
  @Path("/get/{id}")
  public FileDto get(@PathParam long id) {
    ProofOfIdentity contract = ProofOfIdentity.byId(id);
    return AppResponse.fileDownload(contract);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    var identity = ProofOfIdentity.byId(id);
    identity.removeFromClient();
    identity.delete();
    return AppResponse.ok();
  }
}
