package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.PartnerIntermediaryNumber;
import com.quellkunst.nemesis.model.PartnerLogin;
import com.quellkunst.nemesis.service.dto.PartnerIntermediaryNumberDto;
import com.quellkunst.nemesis.service.dto.PartnerLoginDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

@Mapper(config = QuarkusMappingConfig.class)
public interface PartnerIntermediaryNumberMapper {
    PartnerIntermediaryNumberDto toDto(PartnerIntermediaryNumber entity);

    PartnerIntermediaryNumber newEntity(PartnerIntermediaryNumberDto dto);

    @PartnerIntermediaryNumberMapper.GetOrCreatePartnerIntermediaryNumber
    default PartnerIntermediaryNumber getOrCreate(PartnerIntermediaryNumberDto dto) {
        Optional<PartnerIntermediaryNumber> maybe = PartnerIntermediaryNumber.findByIdOptional(dto.getId());
        return maybe.orElseGet(() -> newEntity(dto));
    }

    @AfterMapping
    default void persist(@MappingTarget PartnerIntermediaryNumber entity) {
        entity.persist();
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface GetOrCreatePartnerIntermediaryNumber {}
}
