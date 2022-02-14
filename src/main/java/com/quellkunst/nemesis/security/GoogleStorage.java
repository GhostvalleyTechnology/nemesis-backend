package com.quellkunst.nemesis.security;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.quellkunst.nemesis.model.CloudFile;
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
  @ConfigProperty(name = "nemesis.google.storage.bucketName")
  String bucketName;

  public void upload(CloudFile entity, AbstractFileBasedDto dto) {
    entity.objectName = UUID.randomUUID().toString();
    try {
      getService().createFrom(getBlobInfo(entity), dto.file);
    } catch (IOException e) {
      Log.error(e);
      throw ExceptionSupplier.serviceUnavailableException("Upload currently not possible").get();
    }
  }

  public URL download(CloudFile entity) {
    return getService()
        .signUrl(getBlobInfo(entity), 5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature());
  }

  public void delete(CloudFile entity) {
    getService().delete(getBlobId(entity));
  }

  private Storage getService() {
    return StorageOptions.newBuilder().build().getService();
  }

  private BlobId getBlobId(CloudFile entity) {
    return BlobId.of(bucketName, entity.objectName);
  }

  private BlobInfo getBlobInfo(CloudFile entity) {
    return BlobInfo.newBuilder(getBlobId(entity)).build();
  }
}
