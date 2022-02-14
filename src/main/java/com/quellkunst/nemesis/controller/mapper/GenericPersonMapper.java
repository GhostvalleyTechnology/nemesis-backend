package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.GenericPerson;
import com.quellkunst.nemesis.service.dto.GenericPersonDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Qualifier;

@Mapper(config = QuarkusMappingConfig.class)
public interface GenericPersonMapper {
  GenericPersonDto toDto(GenericPerson entity);

  GenericPerson newEntity(GenericPersonDto dto);

  @GetOrCreateGenericPerson
  default GenericPerson getOrCreate(GenericPersonDto dto) {
    Optional<GenericPerson> maybe = GenericPerson.findByIdOptional(dto.getId());
    return maybe.orElseGet(() -> newEntity(dto));
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetOrCreateGenericPerson {}
}
