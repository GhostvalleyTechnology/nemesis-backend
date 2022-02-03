package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.model.PartnerServiceType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerDto extends AbstractEntityDto<Partner> {
  String name;
  String website;
  String bank;
  String iban;
  String bic;
  List<PartnerServiceTypeDto> services;
  List<PartnerLoginDto> logins;
  List<PartnerContactDto> contacts;

  protected PartnerDto(Partner entity) {
    super(entity);
  }

  public static PartnerDto of(Partner entity) {
    var dto = new PartnerDto(entity);
    dto.name = entity.name;
    dto.website = entity.website;
    dto.bank = entity.bank;
    dto.iban = entity.iban;
    dto.bic = entity.bic;
    dto.services =
        entity.services.stream().map(PartnerServiceTypeDto::of).collect(Collectors.toList());
    dto.logins = entity.logins.stream().map(PartnerLoginDto::of).collect(Collectors.toList());
    dto.contacts = entity.contacts.stream().map(PartnerContactDto::of).collect(Collectors.toList());
    return dto;
  }

  @Override
  public Partner prepareNewEntity() {
    var entity = new Partner();
    entity.name = name;
    entity.website = website;
    entity.bank = bank;
    entity.iban = iban;
    entity.bic = bic;
    entity.services =
        services.stream()
            .map(dto -> PartnerServiceType.byId(dto.id))
            .collect(Collectors.toCollection(TreeSet::new));
    entity.logins = logins.stream().map(PartnerLoginDto::newEntity).collect(Collectors.toList());
    entity.contacts =
        contacts.stream().map(PartnerContactDto::newEntity).collect(Collectors.toList());
    return entity;
  }

  @Override
  public Partner prepareUpdateEntity() {
    var entity = Partner.byId(id);
    entity.name = name;
    entity.website = website;
    entity.bank = bank;
    entity.iban = iban;
    entity.bic = bic;
    return entity;
  }

  @Override
  public Partner getEntity() {
    return Partner.byId(id);
  }
}
