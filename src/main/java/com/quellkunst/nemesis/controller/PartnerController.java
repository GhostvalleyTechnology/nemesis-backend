package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.controller.mapper.PartnerMapper;
import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.repository.PartnerRepository;
import com.quellkunst.nemesis.service.dto.PartnerDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PartnerController {
  @Inject PartnerMapper mapper;
  @Inject PartnerRepository repository;

  public Partner add(PartnerDto dto) {
    return mapper.newEntity(dto);
  }

  public void update(PartnerDto dto) {
    var entity = repository.byId(dto.getId());
    mapper.updateEntity(dto, entity);
  }
}
