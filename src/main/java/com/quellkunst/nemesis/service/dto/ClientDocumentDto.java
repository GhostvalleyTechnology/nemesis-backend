package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ClientDocument;
import com.quellkunst.nemesis.model.ClientDocumentType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientDocumentDto extends AbstractEntityDto {
  ClientDocumentType type;
  CloudFileDto file;

  public static ClientDocumentDto of(ClientDocument entity) {
    var dto = new ClientDocumentDto();
    dto.id = entity.id;
    dto.createdAt = entity.createdAt;
    return dto;
  }
}
