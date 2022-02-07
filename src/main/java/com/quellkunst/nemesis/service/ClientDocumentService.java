package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.ClientDocument;
import com.quellkunst.nemesis.service.dto.ClientDocumentDto;
import com.quellkunst.nemesis.service.dto.ClientDocumentUploadDto;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Path("/client-document")
@Transactional
public class ClientDocumentService {
  @Inject AppResponse appResponse;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload")
  public ClientDocumentDto upload(@MultipartForm ClientDocumentUploadDto input) {
    Client client = Client.byId(input.clientId);
    var clientDocument = new ClientDocument();
    clientDocument.file = input.persist();
    clientDocument.client = client;
    clientDocument.persist();
    client.clientDocuments.add(clientDocument);
    return ClientDocumentDto.of(clientDocument);
  }

  @GET
  @Path("/get/{id}")
  public Response get(@PathParam long id) {
    ClientDocument document = ClientDocument.byId(id);
    return appResponse.fileDownload(document.file);
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    ClientDocument entity = ClientDocument.byId(id);
    entity.client.clientDocuments.remove(entity);
    return appResponse.deleted(ClientDocument.deleteById(id));
  }
}
