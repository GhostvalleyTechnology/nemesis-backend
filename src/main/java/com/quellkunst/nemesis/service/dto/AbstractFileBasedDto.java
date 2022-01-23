package com.quellkunst.nemesis.service.dto;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractFileBasedDto {
  @FormParam("file")
  @PartType(MediaType.APPLICATION_OCTET_STREAM)
  public InputStream file;

  @FormParam("fileName")
  @PartType(MediaType.TEXT_PLAIN)
  public String fileName;

  public byte[] readFile() {
    try {
      return file.readAllBytes();
    } catch (IOException e) {
      throw new BadRequestException(e);
    }
  }
}
