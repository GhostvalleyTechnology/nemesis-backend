package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.ClientDocumentController;
import com.quellkunst.nemesis.controller.mapper.ClientDocumentMapper;
import com.quellkunst.nemesis.repository.ClientDocumentRepository;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.security.Guard;
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
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject ClientDocumentRepository documentRepository;
  @Inject ClientRepository clientRepository;
  @Inject ClientDocumentController controller;
  @Inject ClientDocumentMapper mapper;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Path("/upload")
  public ClientDocumentDto upload(@MultipartForm ClientDocumentUploadDto dto) {
    var client = clientRepository.byId(dto.clientId);
    return guard.asEmployee(() -> client.supervisor, () -> mapper.toDto(controller.add(dto)));
  }

  @GET
  @Path("/get/{id}")
  public Response get(@PathParam long id) {
    var entity = documentRepository.byId(id);
    return guard.asEmployee(
        () -> entity.client.supervisor, () -> appResponse.fileDownload(entity.file));
  }

  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam long id) {
    var entity = documentRepository.byId(id);
    guard.asEmployee(() -> entity.client.supervisor, () -> controller.delete(entity));
    return appResponse.ok();
  }
}
