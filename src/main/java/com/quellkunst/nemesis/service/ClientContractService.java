package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.ClientContractController;
import com.quellkunst.nemesis.controller.mapper.ClientContractMapper;
import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.repository.ClientContractRepository;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.ClientContractDto;
import com.quellkunst.nemesis.service.dto.ClientContractUploadDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client-contract")
@Transactional
public class ClientContractService {
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject ClientRepository clientRepository;
  @Inject ClientContractController controller;
  @Inject ClientContractRepository repository;
  @Inject ClientContractMapper mapper;

  @POST
  @Path("/add")
  public ClientContractDto add(ClientContractDto dto) {
    return guard.asEmployee(
        () -> clientRepository.byId(dto.getClientId()).supervisor,
        () -> mapper.toDto(controller.add(dto)));
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload-policy")
  public ClientContractDto uploadPolicy(@MultipartForm ClientContractUploadDto dto) {
    var entity =
        guard.asEmployee(
            () -> repository.byId(dto.clientContractId).client.supervisor,
            () -> controller.uploadPolicy(dto));
    return mapper.toDto(entity);
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload-policy-request")
  public ClientContractDto uploadPolicyRequest(@MultipartForm ClientContractUploadDto dto) {
    var entity =
        guard.asEmployee(
            () -> repository.byId(dto.clientContractId).client.supervisor,
            () -> controller.uploadPolicyRequest(dto));
    return mapper.toDto(entity);
  }

  @POST
  @Path("/update")
  public Response update(ClientContractDto dto) {
    guard.asEmployee(
        () -> repository.byId(dto.getId()).client.supervisor, () -> controller.update(dto));
    return appResponse.ok();
  }

  @GET
  @Path("/get-policy/{contractId}")
  public Response getPolicy(@PathParam long contractId) {
    ClientContract contract = repository.byId(contractId);
    return guard.asEmployee(
        () -> contract.client.supervisor, () -> appResponse.fileDownload(contract.policy));
  }

  @GET
  @Path("/get-policy-request/{contractId}")
  public Response getPolicyRequest(@PathParam long contractId) {
    ClientContract contract = repository.byId(contractId);
    return guard.asEmployee(
        () -> contract.client.supervisor, () -> appResponse.fileDownload(contract.policyRequest));
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ClientContract contract = repository.byId(id);
    guard.asEmployee(() -> contract.client.supervisor, () -> controller.delete(contract));
    return appResponse.ok();
  }
}
