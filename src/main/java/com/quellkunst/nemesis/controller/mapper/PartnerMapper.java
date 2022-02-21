package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.model.Partner_;
import com.quellkunst.nemesis.repository.PartnerRepository;
import com.quellkunst.nemesis.service.dto.PartnerDto;
import com.quellkunst.nemesis.service.dto.PartnerReferenceDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.inject.spi.CDI;
import org.mapstruct.*;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {PartnerServiceTypeMapper.class, PartnerLoginMapper.class, PartnerContactMapper.class})
public interface PartnerMapper {
  PartnerDto toDto(Partner entity);

  @ToReferenceDto
  PartnerReferenceDto toReferenceDto(Partner entity);

  @Mappings({
    @Mapping(
        target = Partner_.SERVICES,
        qualifiedBy = PartnerServiceTypeMapper.GetPartnerServiceType.class),
    @Mapping(
        target = Partner_.LOGINS,
        qualifiedBy = PartnerLoginMapper.GetOrCreatePartnerLogin.class),
    @Mapping(
        target = Partner_.CONTACTS,
        qualifiedBy = PartnerContactMapper.GetOrCreatePartnerContact.class)
  })
  Partner newEntity(PartnerDto dto);

  @Mappings({
    @Mapping(
        target = Partner_.SERVICES,
        qualifiedBy = PartnerServiceTypeMapper.GetPartnerServiceType.class),
    @Mapping(
        target = Partner_.LOGINS,
        qualifiedBy = PartnerLoginMapper.GetOrCreatePartnerLogin.class),
    @Mapping(
        target = Partner_.CONTACTS,
        qualifiedBy = PartnerContactMapper.GetOrCreatePartnerContact.class)
  })
  void updateEntity(PartnerDto dto, @MappingTarget Partner entity);

  @GetPartnerThroughReferenceDto
  default Partner get(PartnerReferenceDto dto) {
    return CDI.current().select(PartnerRepository.class).get().findById(dto.getId());
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetPartnerThroughReferenceDto {}

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface ToReferenceDto {}
}
