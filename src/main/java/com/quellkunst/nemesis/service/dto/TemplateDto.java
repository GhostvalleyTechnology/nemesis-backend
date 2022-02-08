package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Template;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class TemplateDto extends AbstractEntityDto<Template> {
  private boolean adminOnly;
  private CloudFileDto cloudFile;

  protected TemplateDto(Template entity) {
    super(entity);
  }

  public static TemplateDto of(Template entity) {
    var dto = new TemplateDto(entity);
    dto.adminOnly = entity.adminOnly;
    dto.cloudFile = CloudFileDto.of(entity.file);
    return dto;
  }

  @Override
  protected Template prepareNewEntity() {
    var entity = new Template();
    entity.adminOnly = adminOnly;
    return entity;
  }

  @Override
  protected Template prepareUpdateEntity() {
    Template entity = Template.byId(id);
    entity.adminOnly = adminOnly;
    return entity;
  }
}
