package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityDto;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface ProofOfIdentityMapper {
  ProofOfIdentityDto toDto(ProofOfIdentity entity);
}
