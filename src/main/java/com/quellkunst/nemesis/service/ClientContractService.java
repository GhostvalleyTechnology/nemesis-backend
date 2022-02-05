package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.ClientContractDto;
import com.quellkunst.nemesis.service.dto.ClientContractUploadDto;
import com.quellkunst.nemesis.service.dto.FileDto;
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

  @POST
  @Path("/add")
  public ClientContractDto add(ClientContractDto dto) {
    return ClientContractDto.of(dto.newEntity());
  }

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload")
  public Response upload(@MultipartForm ClientContractUploadDto input) {
    var contract = ClientContract.byId(input.clientContractId);
    contract.fileId = input.persist();
    contract.fileName = input.fileName;
    return AppResponse.ok();
  }

  @POST
  @Path("/update")
  public Response update(ClientContractDto dto) {
    dto.updateEntity();
    return AppResponse.ok();
  }

  @GET
  @Path("/get/{contractId}")
  public FileDto get(@PathParam long contractId) {
    ClientContract contract = ClientContract.byId(contractId);
    return AppResponse.fileDownload(contract);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    var contract = ClientContract.byId(id);
    contract.client.clientContracts.remove(contract);
    return AppResponse.deleted(ClientContract.deleteById(id));
  }
}
