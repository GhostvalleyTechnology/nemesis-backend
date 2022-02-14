package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.controller.mapper.ClientMapper;
import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.service.dto.ClientDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientController {
  @Inject AppContext context;
  @Inject ClientMapper mapper;
  @Inject ClientRepository repository;

  public Client add(ClientDto dto) {
    var client = mapper.newEntity(dto);
    client.supervisor = context.getCurrentEmployee();
    client.persist();
    Reminder.createNewClientReminders(client);
    return client;
  }

  public void update(ClientDto dto) {
    var client = repository.byId(dto.getId());
    mapper.updateEntity(dto, client);
  }

  public void delete(Client client) {
    client.deleted = true;
  }
}
