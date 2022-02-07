package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@RegisterForReflection
public class TemplateUploadDto extends AbstractFileBasedDto {
  @FormParam("adminOnly")
  @PartType(MediaType.TEXT_PLAIN)
  public boolean adminOnly;
}
