package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.service.dto.ClientContractUploadDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client-contract")
public class ClientContractService {
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  @Path("/add")
  public Response add(@MultipartForm ClientContractUploadDto input) {
    var fileId = input.persist();
    Client client = Client.byId(input.clientId);

    var contract =
        ClientContract.builder()
            .contractNumber(input.contractNumber)
            .fileId(fileId)
            .fileName(input.fileName)
            .legacy(input.legacy)
            .paymentFrequency(input.paymentFrequency)
            .paymentValue(input.paymentValue)
            .contractor(Partner.byId(input.contractorId))
            .build();
    contract.persist();
    client.clientContracts.add(contract);
    return AppResponse.ok();
  }

  @GET
  @Path("/get/{contractId}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  @Transactional
  public Response get(@PathParam long contractId) {
    ClientContract contract = ClientContract.byId(contractId);
    return AppResponse.fileDownload(contract);
  }
}
