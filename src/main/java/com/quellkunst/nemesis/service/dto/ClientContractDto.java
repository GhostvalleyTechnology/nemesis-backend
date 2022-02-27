package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.PaymentFrequency;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientContractDto extends AbstractEntityDto {
  long clientId;
  boolean legacy;
  String contractNumber;
  LocalDate contractDate;
  long paymentValue;
  PaymentFrequency paymentFrequency;
  PartnerReferenceDto contractor;
  PartnerServiceTypeDto serviceType;
  CloudFileDto policyRequest;
  CloudFileDto policy;
}
