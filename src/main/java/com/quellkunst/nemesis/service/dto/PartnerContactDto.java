package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.PartnerContact;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerContactDto extends AbstractEntityDto<PartnerContact> {
  String name;
  String email;
  String phone;
  String remarks;

  protected PartnerContactDto(PartnerContact entity) {
    super(entity);
  }

  public static PartnerContactDto of(PartnerContact entity) {
    var dto = new PartnerContactDto(entity);
    dto.name = entity.name;
    dto.email = entity.email;
    dto.phone = entity.phone;
    dto.remarks = entity.remarks;
    return dto;
  }

  @Override
  public PartnerContact prepareNewEntity() {
    return mapValues(new PartnerContact());
  }

  protected PartnerContact mapValues(PartnerContact entity) {
    entity.name = name;
    entity.email = email;
    entity.phone = phone;
    entity.remarks = remarks;
    return entity;
  }

  @Override
  public PartnerContact prepareUpdateEntity() {
    return mapValues(PartnerContact.byId(id));
  }
}
