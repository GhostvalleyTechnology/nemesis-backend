package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.Template;
import com.quellkunst.nemesis.service.dto.TemplateDto;
import org.mapstruct.Mapper;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {CloudFileMapper.class})
public interface TemplateMapper {
  TemplateDto toDto(Template entity);
}
