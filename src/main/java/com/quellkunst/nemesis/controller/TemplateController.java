package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.model.Template;
import com.quellkunst.nemesis.repository.TemplateRepository;
import com.quellkunst.nemesis.service.dto.TemplateDto;
import com.quellkunst.nemesis.service.dto.TemplateUploadDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TemplateController {
  @Inject TemplateRepository templateRepository;
  @Inject CloudFileController cloudFileController;

  public Template add(TemplateUploadDto input) {
    var cloudFile = cloudFileController.add(input);
    var entity = new Template();
    entity.file = cloudFile;
    entity.adminOnly = input.adminOnly;
    entity.persist();
    return entity;
  }

  public void update(TemplateDto dto) {
    var entity = templateRepository.byId(dto.getId());
    entity.adminOnly = dto.isAdminOnly();
  }
}
