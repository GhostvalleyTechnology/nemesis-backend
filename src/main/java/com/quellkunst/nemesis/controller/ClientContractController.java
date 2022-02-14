package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.controller.mapper.ClientContractMapper;
import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.repository.ClientContractRepository;
import com.quellkunst.nemesis.service.dto.ClientContractDto;
import com.quellkunst.nemesis.service.dto.ClientContractUploadDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientContractController {
  @Inject CloudFileController cloudFileController;
  @Inject ClientContractRepository repository;
  @Inject ClientContractMapper mapper;

  public ClientContract add(ClientContractDto dto) {
    var entity = mapper.newEntity(dto);
    entity.client.clientContracts.add(entity);
    return entity;
  }

  public void uploadPolicy(ClientContractUploadDto dto) {
    var entity = repository.byId(dto.clientContractId);
    entity.policy = cloudFileController.add(dto);
  }

  public void uploadPolicyRequest(ClientContractUploadDto dto) {
    var entity = repository.byId(dto.clientContractId);
    entity.policyRequest = cloudFileController.add(dto);
  }

  public void update(ClientContractDto dto) {
    var entity = repository.byId(dto.getId());
    mapper.updateEntity(dto, entity);
  }

  public void delete(ClientContract contract) {
    contract.client.clientContracts.remove(contract);
    if (contract.policyRequest != null) {
      cloudFileController.delete(contract.policyRequest);
    }
    if (contract.policy != null) {
      cloudFileController.delete(contract.policy);
    }
    contract.delete();
  }
}
