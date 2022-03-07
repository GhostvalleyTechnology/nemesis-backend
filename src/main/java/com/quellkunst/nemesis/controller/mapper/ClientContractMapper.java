package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.model.ClientContract_;
import com.quellkunst.nemesis.service.dto.ClientContractDto;
import org.mapstruct.*;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {
      ClientMapper.class,
      PartnerMapper.class,
      PartnerServiceTypeMapper.class,
      CloudFileMapper.class
    })
public interface ClientContractMapper {
  @Mappings({
    @Mapping(target = ClientContract_.CONTRACTOR, qualifiedBy = PartnerMapper.ToReferenceDto.class)
  })
  ClientContractDto toDto(ClientContract entity);

  @Mappings({
    @Mapping(
        target = ClientContract_.CLIENT,
        source = "clientId",
        qualifiedBy = ClientMapper.GetClientById.class),
    @Mapping(
        target = ClientContract_.CONTRACTOR,
        qualifiedBy = PartnerMapper.GetPartnerThroughReferenceDto.class),
    @Mapping(
        target = ClientContract_.SERVICE_TYPE,
        qualifiedBy = PartnerServiceTypeMapper.GetPartnerServiceType.class)
  })
  ClientContract newEntity(ClientContractDto dto);

  @Mappings({
    @Mapping(target = ClientContract_.CLIENT, ignore = true),
    @Mapping(
        target = ClientContract_.CONTRACTOR,
        qualifiedBy = PartnerMapper.GetPartnerThroughReferenceDto.class),
    @Mapping(
        target = ClientContract_.SERVICE_TYPE,
        qualifiedBy = PartnerServiceTypeMapper.GetPartnerServiceType.class),
    @Mapping(target = ClientContract_.POLICY_REQUEST, ignore = true),
    @Mapping(target = ClientContract_.POLICY, ignore = true),
  })
  void updateEntity(ClientContractDto dto, @MappingTarget ClientContract entity);

  @AfterMapping
  default void persist(@MappingTarget ClientContract entity) {
    entity.persist();
  }
}
