package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.model.CloudFile;
import com.quellkunst.nemesis.security.GoogleStorage;
import com.quellkunst.nemesis.service.dto.AbstractFileBasedDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CloudFileController {
  @Inject GoogleStorage googleStorage;

  public CloudFile add(AbstractFileBasedDto dto) {
    var entity = new CloudFile();
    entity.fileName = dto.getFileName();
    entity.fileExtension = dto.getFileExtension();
    googleStorage.upload(entity, dto);
    return entity;
  }

  public void delete(CloudFile entity) {
    googleStorage.delete(entity);
  }
}
