package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.controller.mapper.PartnerServiceTypeMapper;
import com.quellkunst.nemesis.model.PartnerServiceType;
import com.quellkunst.nemesis.service.dto.PartnerServiceTypeDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PartnerServiceTypeController {
  @Inject PartnerServiceTypeMapper mapper;

  public PartnerServiceType add(PartnerServiceTypeDto dto) {
    return mapper.newEntity(dto);
  }

  public boolean delete(long id) {
    return PartnerServiceType.deleteById(id);
  }
}
