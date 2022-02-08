package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.repository.TemplateRepository;
import com.quellkunst.nemesis.service.dto.TemplateDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TemplateController {
    @Inject
    TemplateRepository templateRepository;

    public void update(TemplateDto dto) {

    }
}
