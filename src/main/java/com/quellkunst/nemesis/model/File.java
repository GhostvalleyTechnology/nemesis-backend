package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
public class File extends EntityBase {
  public String name;
  public String extension;

  @Lob
  @Basic(fetch = LAZY)
  public byte[] data;

  @Builder
  public File(String name, String extension, byte[] data) {
    this.name = name;
    this.extension = extension;
    this.data = data;
  }

  public static File byId(long id) {
    Optional<File> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
