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
    entity.client.addContract(entity);
    entity.client.persist();
    return entity;
  }

  public ClientContract uploadPolicy(ClientContractUploadDto dto) {
    var entity = repository.byId(dto.clientContractId);
    entity.policy = cloudFileController.add(dto);
    return entity;
  }

  public ClientContract uploadPolicyRequest(ClientContractUploadDto dto) {
    var entity = repository.byId(dto.clientContractId);
    entity.policyRequest = cloudFileController.add(dto);
    return entity;
  }

  public void update(ClientContractDto dto) {
    var entity = repository.byId(dto.getId());
    mapper.updateEntity(dto, entity);
    if (dto.getPolicy() == null) {
      entity.policy = null;
    }
    if (dto.getPolicyRequest() == null) {
      entity.policyRequest = null;
    }
  }

  public void delete(ClientContract contract) {
    contract.client.contracts.remove(contract);
    if (contract.policyRequest != null) {
      cloudFileController.delete(contract.policyRequest);
    }
    if (contract.policy != null) {
      cloudFileController.delete(contract.policy);
    }
    contract.delete();
  }
}
