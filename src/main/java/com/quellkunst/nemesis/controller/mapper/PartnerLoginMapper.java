package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.PartnerLogin;
import com.quellkunst.nemesis.service.dto.PartnerLoginDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;

@Mapper(config = QuarkusMappingConfig.class)
public interface PartnerLoginMapper {
  PartnerLoginDto toDto(PartnerLogin entity);

  PartnerLogin newEntity(PartnerLoginDto dto);

  @GetOrCreatePartnerLogin
  default PartnerLogin getOrCreate(PartnerLoginDto dto) {
    Optional<PartnerLogin> maybe = PartnerLogin.findByIdOptional(dto.getId());
    return maybe.orElseGet(() -> newEntity(dto));
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetOrCreatePartnerLogin {}
}
