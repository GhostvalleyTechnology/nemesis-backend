package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ClientDocument;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientDocumentDto extends AbstractEntityDto<ClientDocument> {
  String fileName;

  public static ClientDocumentDto of(ClientDocument entity) {
    var dto = new ClientDocumentDto();
    dto.id = entity.id;
    dto.createdAt = entity.createdAt;
    dto.fileName = entity.file.fileName;
    return dto;
  }

  @Override
  protected ClientDocument prepareNewEntity() {
    return new ClientDocument();
  }

  @Override
  protected ClientDocument prepareUpdateEntity() {
    return ClientDocument.byId(id);
  }
}
