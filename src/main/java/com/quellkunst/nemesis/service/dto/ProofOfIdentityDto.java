package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ProofOfIdentityType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ProofOfIdentityDto extends AbstractEntityDto {
  ProofOfIdentityType type;
  CloudFileDto file;
}
