package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.*;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientContractDto extends AbstractEntityDto<ClientContract> {
  long clientId;
  boolean legacy;
  String contractNumber;
  long paymentValue;
  PaymentFrequency paymentFrequency;
  PartnerReferenceDto contractor;
  PartnerServiceTypeDto serviceType;

  protected ClientContractDto(ClientContract entity) {
    super(entity);
  }

  public static ClientContractDto of(ClientContract entity) {
    var dto = new ClientContractDto(entity);
    dto.clientId = entity.client.id;
    dto.legacy = entity.legacy;
    dto.contractNumber = entity.contractNumber;
    dto.paymentValue = entity.paymentValue;
    dto.paymentFrequency = entity.paymentFrequency;
    dto.contractor = PartnerReferenceDto.of(entity.contractor);
    dto.serviceType = PartnerServiceTypeDto.of(entity.serviceType);
    return dto;
  }

  @Override
  protected ClientContract prepareNewEntity() {
    var entity = new ClientContract();
    mapValues(entity);
    entity.client = Client.byId(clientId);
    entity.client.clientContracts.add(entity);
    return entity;
  }

  private ClientContract mapValues(ClientContract entity) {
    entity.legacy = legacy;
    entity.contractNumber = contractNumber;
    entity.paymentValue = paymentValue;
    entity.paymentFrequency = paymentFrequency;
    entity.contractor = Partner.byId(contractor.id);
    entity.serviceType = PartnerServiceType.byId(serviceType.id);
    return entity;
  }

  @Override
  protected ClientContract prepareUpdateEntity() {
    return mapValues(ClientContract.byId(id));
  }
}
