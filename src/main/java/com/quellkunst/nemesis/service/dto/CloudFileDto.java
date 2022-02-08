package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.CloudFile;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class CloudFileDto {
  private String objectName;
  private String fileName;
  private String fileExtension;

  public static CloudFileDto of(CloudFile entity) {
    var dto = new CloudFileDto();
    dto.objectName = entity.objectName;
    dto.fileName = entity.fileName;
    dto.fileExtension = entity.fileExtension;
    return dto;
  }
}
