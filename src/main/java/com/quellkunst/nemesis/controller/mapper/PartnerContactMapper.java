package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.PartnerContact;
import com.quellkunst.nemesis.service.dto.PartnerContactDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;

@Mapper(config = QuarkusMappingConfig.class)
public interface PartnerContactMapper {
  PartnerContactDto toDto(PartnerContact entity);

  PartnerContact newEntity(PartnerContactDto dto);

  @GetOrCreatePartnerContact
  default PartnerContact getOrCreate(PartnerContactDto dto) {
    Optional<PartnerContact> maybe = PartnerContact.findByIdOptional(dto.getId());
    return maybe.orElseGet(() -> newEntity(dto));
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetOrCreatePartnerContact {}
}
