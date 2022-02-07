package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

@RegisterForReflection
public abstract class AbstractFileBasedDto {
  @FormParam("file")
  @PartType(MediaType.APPLICATION_OCTET_STREAM)
  public InputStream file;

  @FormParam("fileName")
  @PartType(MediaType.TEXT_PLAIN)
  public String fileName;

  @FormParam("fileExtension")
  @PartType(MediaType.TEXT_PLAIN)
  public String fileExtension;

  public boolean hasFile() {
    return file != null;
  }

  public byte[] readFile() {
    try {
      return file.readAllBytes();
    } catch (IOException e) {
      throw new BadRequestException(e);
    }
  }

  public long persist() {
    var file = File.builder().name(fileName).extension(fileExtension).data(readFile()).build();
    file.persist();
    return file.id;
  }
}