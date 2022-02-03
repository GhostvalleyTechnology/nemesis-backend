package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@RegisterForReflection
public class TemplateUploadDto extends AbstractFileBasedDto {
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
