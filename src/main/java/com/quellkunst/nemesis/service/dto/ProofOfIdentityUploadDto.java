package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ProofOfIdentityType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import lombok.Getter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@RegisterForReflection
@Getter
public class ProofOfIdentityUploadDto extends AbstractFileBasedDto {
  @FormParam("clientId")
  @PartType(MediaType.TEXT_PLAIN)
  public long clientId;

  @FormParam("type")
  @PartType(MediaType.TEXT_PLAIN)
  public ProofOfIdentityType type;
}
