package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.File;
import com.quellkunst.nemesis.service.dto.AbstractFileBasedDto;

public class FileUpload {
  private FileUpload() {}

  public static long persist(AbstractFileBasedDto dto) {
    var file = File.builder().name(dto.fileName).data(dto.readFile()).build();
    file.persist();
    return file.id;
  }
}
