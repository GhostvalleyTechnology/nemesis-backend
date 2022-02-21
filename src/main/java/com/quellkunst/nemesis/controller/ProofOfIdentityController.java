package com.quellkunst.nemesis.controller;

import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.repository.ClientRepository;
import com.quellkunst.nemesis.service.dto.ProofOfIdentityUploadDto;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ProofOfIdentityController {
  @Inject CloudFileController cloudFileController;
  @Inject ClientRepository clientRepository;

  public ProofOfIdentity add(ProofOfIdentityUploadDto dto) {
    var client = clientRepository.byId(dto.clientId);
    var cloudFile = cloudFileController.add(dto);
    var proofOfIdentity = new ProofOfIdentity();
    proofOfIdentity.type = dto.type;
    proofOfIdentity.file = cloudFile;
    proofOfIdentity.persist();
    client.proofOfIdentities.add(proofOfIdentity);
    return proofOfIdentity;
  }

  public void delete(ProofOfIdentity identity) {
    identity.client.proofOfIdentities.remove(identity);
    identity.delete();
  }
}
