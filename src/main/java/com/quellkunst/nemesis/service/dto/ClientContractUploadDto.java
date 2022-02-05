package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

@RegisterForReflection
public class ClientContractUploadDto extends AbstractFileBasedDto {
  @FormParam("clientContractId")
  @PartType(MediaType.TEXT_PLAIN)
  public long clientContractId;
}
