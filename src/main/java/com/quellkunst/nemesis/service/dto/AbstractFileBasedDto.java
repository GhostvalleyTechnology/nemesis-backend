package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.CloudFile;
import com.quellkunst.nemesis.security.GoogleStorage;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.io.InputStream;
import javax.enterprise.inject.spi.CDI;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

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

  public CloudFile persist() {
    var entity = CloudFile.builder().fileName(fileName).fileExtension(fileExtension).build();
    CDI.current().select(GoogleStorage.class).get().upload(entity, this);
    return entity;
  }
}
