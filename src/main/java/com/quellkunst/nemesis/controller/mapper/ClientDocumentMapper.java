package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.ClientDocument;
import com.quellkunst.nemesis.service.dto.ClientDocumentDto;
import org.mapstruct.Mapper;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {CloudFileMapper.class})
public interface ClientDocumentMapper {
  ClientDocumentDto toDto(ClientDocument entity);
}
