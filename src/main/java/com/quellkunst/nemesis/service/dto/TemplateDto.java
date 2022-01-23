package com.quellkunst.nemesis.service.dto;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

public class TemplateDto extends AbstractFileBasedDto {
  @FormParam("adminOnly")
  @PartType(MediaType.TEXT_PLAIN)
  public boolean adminOnly;

  public byte[] readFile() {
    try {
      return file.readAllBytes();
    } catch (IOException e) {
      throw new BadRequestException(e);
    }
  }
}
