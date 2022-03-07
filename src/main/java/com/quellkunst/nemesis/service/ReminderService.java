package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.mapper.ReminderMapper;
import com.quellkunst.nemesis.repository.ReminderRepository;
import com.quellkunst.nemesis.service.dto.ReminderDto;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Transactional
@Path("/reminder")
public class ReminderService {
  @Inject ReminderRepository repository;
  @Inject ReminderMapper mapper;

  @GET
  public List<ReminderDto> list() {
    return repository.getEmployeeReminders().map(mapper::toDto).collect(Collectors.toList());
  }
}
