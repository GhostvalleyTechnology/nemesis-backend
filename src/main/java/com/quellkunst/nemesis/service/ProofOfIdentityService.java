package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.repository.ProofOfIdentityRepository;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityDto;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityUploadDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/proof-of-identity")
@Transactional
public class ProofOfIdentityService {
  @Inject AppResponse appResponse;
  @Inject ProofOfIdentityRepository proofOfIdentityRepository;
  @Inject ClientRepository clientRepository;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/add")
  public ProofOfIdentityDto add(@MultipartForm ProofOfIdentityUploadDto input) {
    var googleFile = input.persist();
    var proofOfIdentity = ProofOfIdentity.builder().type(input.type).file(googleFile).build();
    proofOfIdentity.persist();
    Client client = clientRepository.byId(input.clientId);
    client.proofOfIdentities.add(proofOfIdentity);
    return ProofOfIdentityDto.of(proofOfIdentity);
  }

  @GET
  @Path("/get/{id}")
  public Response get(@PathParam long id) {
    ProofOfIdentity contract = proofOfIdentityRepository.byId(id);
    return appResponse.fileDownload(contract.file);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ProofOfIdentity identity = proofOfIdentityRepository.byId(id);
    identity.removeFromClient();
    identity.delete();
    return appResponse.ok();
  }
}
