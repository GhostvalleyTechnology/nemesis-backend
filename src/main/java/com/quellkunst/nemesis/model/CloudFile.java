package com.quellkunst.nemesis.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Embeddable
@AttributeOverrides({
  @AttributeOverride(name = "objectName", column = @Column(name = "cloud_object_name")),
  @AttributeOverride(name = "fileName", column = @Column(name = "cloud_file_name")),
  @AttributeOverride(name = "fileExtension", column = @Column(name = "cloud_file_extension"))
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CloudFile {
  public String objectName;
  public String fileName;
  public String fileExtension;
}
