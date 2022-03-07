package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.CloudFile;
import com.quellkunst.nemesis.service.dto.CloudFileDto;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface CloudFileMapper {
  CloudFileDto toDto(CloudFile entity);
}
