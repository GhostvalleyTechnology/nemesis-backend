package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.ProofOfIdentityController;
import com.quellkunst.nemesis.controller.mapper.ProofOfIdentityMapper;
import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.repository.ProofOfIdentityRepository;
import com.quellkunst.nemesis.security.Guard;
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
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject ClientRepository clientRepository;
  @Inject ProofOfIdentityRepository proofOfIdentityRepository;
  @Inject ProofOfIdentityController proofOfIdentityController;
  @Inject ProofOfIdentityMapper proofOfIdentityMapper;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/add")
  public ProofOfIdentityDto add(@MultipartForm ProofOfIdentityUploadDto input) {
    return guard.asEmployee(
        () -> clientRepository.byId(input.getClientId()).supervisor,
        () -> proofOfIdentityMapper.toDto(proofOfIdentityController.add(input)));
  }

  @GET
  @Path("/get/{id}")
  public Response get(@PathParam long id) {
    ProofOfIdentity identity = proofOfIdentityRepository.byId(id);
    return guard.asEmployee(
        () -> identity.client.supervisor, () -> appResponse.fileDownload(identity.file));
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ProofOfIdentity identity = proofOfIdentityRepository.byId(id);
    guard.asEmployee(
        () -> identity.client.supervisor, () -> proofOfIdentityController.delete(identity));
    return appResponse.ok();
  }
}
