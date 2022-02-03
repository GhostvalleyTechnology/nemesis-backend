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
  boolean adminOnly;
  String fileName;

  protected TemplateDto(Template entity) {
    super(entity);
  }

  public static TemplateDto of(Template entity) {
    var dto = new TemplateDto(entity);
    dto.adminOnly = entity.adminOnly;
    dto.fileName = entity.fileName;
    return dto;
  }

  @Override
  protected Template prepareNewEntity() {
    var entity = new Template();
    entity.adminOnly = adminOnly;
    entity.fileName = fileName;
    return entity;
  }

  @Override
  protected Template prepareUpdateEntity() {
    var entity = Template.byId(id);
    entity.adminOnly = adminOnly;
    entity.fileName = fileName;
    return entity;
  }

  @Override
  public Template getEntity() {
    return Template.byId(id);
  }
}
