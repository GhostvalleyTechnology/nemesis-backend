package com.quellkunst.nemesis.service.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerReferenceDto extends AbstractEntityDto {
  String name;
  List<PartnerServiceTypeDto> services = Collections.emptyList();
}
