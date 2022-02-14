package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.io.InputStream;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import lombok.Getter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@RegisterForReflection
@Getter
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
}
