package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.repository.ClientContractRepository;
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
  @Inject ClientContractRepository contractRepository;

  @POST
  @Path("/add")
  public ClientContractDto add(ClientContractDto dto) {
    return ClientContractDto.of(dto.newEntity());
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload-policy")
  public Response uploadPolicy(@MultipartForm ClientContractUploadDto input) {
    ClientContract contract = contractRepository.byId(input.clientContractId);
    contract.policy = input.persist();
    return appResponse.ok();
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload-policy-request")
  public Response uploadPolicyRequest(@MultipartForm ClientContractUploadDto input) {
    ClientContract contract = contractRepository.byId(input.clientContractId);
    contract.policyRequest = input.persist();
    return appResponse.ok();
  }

  @POST
  @Path("/update")
  public Response update(ClientContractDto dto) {
    dto.updateEntity();
    return appResponse.ok();
  }

  @GET
  @Path("/get-policy/{contractId}")
  public Response getPolicy(@PathParam long contractId) {
    ClientContract contract = contractRepository.byId(contractId);
    return appResponse.fileDownload(contract.policy);
  }

  @GET
  @Path("/get-policy-request/{contractId}")
  public Response getPolicyRequest(@PathParam long contractId) {
    ClientContract contract = contractRepository.byId(contractId);
    return appResponse.fileDownload(contract.policyRequest);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ClientContract contract = contractRepository.byId(id);
    contract.client.clientContracts.remove(contract);
    return appResponse.deleted(ClientContract.deleteById(id));
  }
}
