package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ClientDocument;
import com.quellkunst.nemesis.service.dto.ClientDocumentDto;
import com.quellkunst.nemesis.service.dto.ClientDocumentUploadDto;
import com.quellkunst.nemesis.service.dto.FileDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/client-document")
@Transactional
public class ClientDocumentService {
  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload")
  public ClientDocumentDto upload(@MultipartForm ClientDocumentUploadDto input) {
    var client = Client.byId(input.clientId);
    var clientDocument = new ClientDocument();
    clientDocument.fileId = input.persist();
    clientDocument.fileName = input.fileName;
    clientDocument.client = client;
    clientDocument.persist();
    client.clientDocuments.add(clientDocument);
    return ClientDocumentDto.of(clientDocument);
  }

  @GET
  @Path("/get/{id}")
  public FileDto get(@PathParam long id) {
    ClientDocument contract = ClientDocument.byId(id);
    return AppResponse.fileDownload(contract);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    var entity = ClientDocument.byId(id);
    entity.client.clientDocuments.remove(entity);
    return AppResponse.deleted(ClientDocument.deleteById(id));
  }
}
