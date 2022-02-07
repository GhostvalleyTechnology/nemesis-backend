package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@RegisterForReflection
public class ClientDocumentUploadDto extends AbstractFileBasedDto {
  @FormParam("clientId")
  @PartType(MediaType.TEXT_PLAIN)
  public long clientId;
}
