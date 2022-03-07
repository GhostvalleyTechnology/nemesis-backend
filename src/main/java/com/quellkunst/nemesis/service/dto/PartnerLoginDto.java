package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerLoginDto extends AbstractEntityDto {
  String link;
  String username;
  String password;
  boolean adminOnly;
}
