package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.GenericPerson;
import com.quellkunst.nemesis.service.dto.GenericPersonDto;
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
public interface GenericPersonMapper {
  GenericPersonDto toDto(GenericPerson entity);

  GenericPerson newEntity(GenericPersonDto dto);

  void updateEntity(GenericPersonDto dto, @MappingTarget GenericPerson entity);

  @AfterMapping
  default void persist(@MappingTarget GenericPerson entity) {
    entity.persist();
  }

  @GetOrCreateGenericPerson
  default GenericPerson getOrCreate(GenericPersonDto dto) {
    if (dto == null) {
      return null;
    }
    Optional<GenericPerson> maybe = GenericPerson.findByIdOptional(dto.getId());
    var entity = maybe.orElseGet(() -> newEntity(dto));
    updateEntity(dto, entity);
    return entity;
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetOrCreateGenericPerson {}
}
