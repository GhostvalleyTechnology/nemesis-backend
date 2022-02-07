package com.quellkunst.nemesis.security;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.quellkunst.nemesis.model.GoogleFile;
import com.quellkunst.nemesis.service.dto.AbstractFileBasedDto;
import io.quarkus.logging.Log;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GoogleStorage {
  @ConfigProperty(name = "nemesis.google.storage.projectId")
  String projectId;

  @ConfigProperty(name = "nemesis.google.storage.bucketName")
  String bucketName;

  public void upload(GoogleFile entity, AbstractFileBasedDto dto) {
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    entity.objectName = UUID.randomUUID().toString();
    BlobId blobId = BlobId.of(bucketName, entity.objectName);
    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    try {
      storage.createFrom(blobInfo, dto.file);
    } catch (IOException e) {
      Log.error(e);
      ExceptionSupplier.serviceUnavailableException("Upload currently not possible");
    }
  }

  public URL download(GoogleFile entity) {
    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
    BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(bucketName, entity.objectName)).build();
    return storage.signUrl(blobInfo, 5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
  }
}
