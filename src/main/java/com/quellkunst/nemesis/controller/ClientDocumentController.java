package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.model.ClientDocument;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.service.dto.ClientDocumentUploadDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientDocumentController {
  @Inject CloudFileController cloudFileController;
  @Inject ClientRepository clientRepository;

  public ClientDocument add(ClientDocumentUploadDto dto) {
    var client = clientRepository.byId(dto.clientId);
    var cloudFile = cloudFileController.add(dto);
    var clientDocument =
        ClientDocument.builder().client(client).file(cloudFile).type(dto.type).build();
    client.clientDocuments.add(clientDocument);
    return clientDocument;
  }

  public void delete(ClientDocument entity) {
    cloudFileController.delete(entity.file);
    entity.client.clientDocuments.remove(entity);
    entity.delete();
  }
}
