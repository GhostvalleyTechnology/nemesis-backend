package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Partner;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerReferenceDto extends AbstractEntityDto<Partner> {
  String name;

  protected PartnerReferenceDto(Partner entity) {
    super(entity);
  }

  public static PartnerReferenceDto of(Partner entity) {
    var dto = new PartnerReferenceDto(entity);
    dto.name = entity.name;
    return dto;
  }

  @Override
  public Partner prepareNewEntity() {
    var entity = new Partner();
    entity.name = name;
    return entity;
  }

  @Override
  public Partner prepareUpdateEntity() {
    var entity = Partner.byId(id);
    entity.name = name;
    return entity;
  }

  @Override
  public Partner getEntity() {
    return Partner.byId(id);
  }
}
