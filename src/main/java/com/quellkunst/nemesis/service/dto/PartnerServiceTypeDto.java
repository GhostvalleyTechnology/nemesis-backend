package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.PartnerServiceType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerServiceTypeDto extends AbstractEntityDto<PartnerServiceType> {
  String service;

  protected PartnerServiceTypeDto(PartnerServiceType entity) {
    super(entity);
  }

  public static PartnerServiceTypeDto of(PartnerServiceType entity) {
    var dto = new PartnerServiceTypeDto(entity);
    dto.service = entity.service;
    return dto;
  }

  @Override
  public PartnerServiceType prepareNewEntity() {
    var entity = new PartnerServiceType();
    entity.service = service;
    return entity;
  }

  @Override
  public PartnerServiceType prepareUpdateEntity() {
    PartnerServiceType entity = PartnerServiceType.byId(id);
    entity.service = service;
    return entity;
  }
}
