package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.Identifiable;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public abstract class AbstractEntityDto implements Identifiable {
  long id;
  LocalDateTime createdAt;

  @Override
  public long getId() {
    return id;
  }
}
