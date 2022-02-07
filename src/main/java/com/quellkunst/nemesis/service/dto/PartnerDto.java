package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.model.PartnerServiceType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    return mapValues(new Partner());
  }

  @Override
  public Partner prepareUpdateEntity() {
    return mapValues(Partner.byId(id));
  }

  private Partner mapValues(Partner entity) {
    entity.name = name;
    entity.website = website;
    entity.bank = bank;
    entity.iban = iban;
    entity.bic = bic;
    entity.services =
        services.stream()
            .map(t -> (PartnerServiceType) PartnerServiceType.byId(t.id))
            .collect(Collectors.toCollection(TreeSet::new));
    entity.logins =
        logins.stream().map(PartnerLoginDto::createOrUpdateEntity).collect(Collectors.toList());
    entity.contacts =
        contacts.stream().map(PartnerContactDto::createOrUpdateEntity).collect(Collectors.toList());
    return entity;
  }
}
