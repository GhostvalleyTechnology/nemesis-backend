package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Partner;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerReferenceDto extends AbstractEntityDto<Partner> {
  String name;
  List<PartnerServiceTypeDto> services;

  protected PartnerReferenceDto(Partner entity) {
    super(entity);
  }

  public static PartnerReferenceDto of(Partner entity) {
    var dto = new PartnerReferenceDto(entity);
    dto.name = entity.name;
    dto.services =
        entity.services.stream().map(PartnerServiceTypeDto::of).collect(Collectors.toList());
    return dto;
  }

  @Override
  public Partner prepareNewEntity() {
    throw new IllegalArgumentException("Don't create a new entity with a reference!");
  }

  @Override
  public Partner prepareUpdateEntity() {
    throw new IllegalArgumentException("Don't update a new entity with a reference!");
  }
}
