package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.*;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientDto extends AbstractPersonDto<Client> {
  String supervisor;
  boolean deleted;
  Boolean militaryServiceDone;
  Boolean smoker;
  Boolean pets;
  String petsRemarks;
  MaritalStatus maritalStatus;
  String homeRemarks;
  String bank;
  String iban;
  String bic;
  GenericPersonDto partner;
  List<GenericPersonDto> children;
  List<ClientContractDto> contracts;
  List<ClientDocumentDto> documents;
  List<ProofOfIdentityDto> proofOfIdentities;

  protected ClientDto(Client entity) {
    super(entity);
  }

  public static ClientDto of(Client entity) {
    var dto = new ClientDto(entity);
    mapEntityToDto(dto, entity);
    dto.supervisor = entity.supervisor.name;
    dto.deleted = entity.deleted;
    dto.militaryServiceDone = entity.militaryServiceDone;
    dto.smoker = entity.smoker;
    dto.pets = entity.pets;
    dto.petsRemarks = entity.petsRemarks;
    dto.maritalStatus = entity.maritalStatus;
    dto.homeRemarks = entity.homeRemarks;
    dto.partner = GenericPersonDto.of(entity.partner);
    dto.children = entity.children.stream().map(GenericPersonDto::of).collect(Collectors.toList());
    dto.contracts =
        entity.clientContracts.stream().map(ClientContractDto::of).collect(Collectors.toList());
    dto.documents =
        entity.clientDocuments.stream().map(ClientDocumentDto::of).collect(Collectors.toList());
    dto.proofOfIdentities =
        entity.proofOfIdentities.stream().map(ProofOfIdentityDto::of).collect(Collectors.toList());
    dto.bank = entity.bank;
    dto.iban = entity.iban;
    dto.bic = entity.bic;
    return dto;
  }

  @Override
  protected Client prepareNewEntity() {
    return mapClientValues(mapPersonValues(new Client()));
  }

  @Override
  protected Client prepareUpdateEntity() {
    return mapClientValues(mapPersonValues(Client.byId(id)));
  }

  private Client mapClientValues(Client entity) {
    entity.militaryServiceDone = militaryServiceDone;
    entity.smoker = smoker;
    entity.pets = pets;
    entity.petsRemarks = petsRemarks;
    entity.maritalStatus = maritalStatus;
    entity.homeRemarks = homeRemarks;
    entity.bank = bank;
    entity.iban = iban;
    entity.bic = bic;

    entity.partner = partner.createOrUpdateEntity();
    entity.children =
        children.stream().map(GenericPersonDto::createOrUpdateEntity).collect(Collectors.toList());
    entity.clientContracts =
        contracts.stream()
            .map(c -> (ClientContract) ClientContract.byId(c.id))
            .collect(Collectors.toList());
    entity.clientDocuments =
        documents.stream()
            .map(d -> (ClientDocument) ClientDocument.byId(d.id))
            .collect(Collectors.toList());
    entity.proofOfIdentities =
        proofOfIdentities.stream()
            .map(i -> (ProofOfIdentity) ProofOfIdentity.byId(i.id))
            .collect(Collectors.toList());
    return entity;
  }
}
