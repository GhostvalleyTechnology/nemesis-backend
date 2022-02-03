package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.model.Partner;
import com.quellkunst.nemesis.model.PartnerServiceType;
import com.quellkunst.nemesis.model.PaymentFrequency;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientContractDto extends AbstractEntityDto<ClientContract> {
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
    return mapValues(new ClientContract());
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

  @Override
  public ClientContract getEntity() {
    return ClientContract.byId(id);
  }
}
