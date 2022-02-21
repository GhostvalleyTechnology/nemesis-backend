package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.PartnerServiceType;
import com.quellkunst.nemesis.security.ExceptionSupplier;
import com.quellkunst.nemesis.service.dto.PartnerServiceTypeDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;

@Mapper(config = QuarkusMappingConfig.class)
public interface PartnerServiceTypeMapper {
  PartnerServiceTypeDto toDto(PartnerServiceType entity);

  PartnerServiceType newEntity(PartnerServiceTypeDto dto);

  @GetPartnerServiceType
  default PartnerServiceType get(PartnerServiceTypeDto dto) {
    Optional<PartnerServiceType> maybe = PartnerServiceType.findByIdOptional(dto.getId());
    return maybe.orElseThrow(ExceptionSupplier.notFoundException("Service not found!"));
  }

  @AfterMapping
  default void persist(@MappingTarget PartnerServiceType entity) {
    entity.persist();
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetPartnerServiceType {}
}
