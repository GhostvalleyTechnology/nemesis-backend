package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class ClientContract extends FileEntityBase {
  public boolean legacy;
  public String contractNumber;
  public long paymentValue;
  public PaymentFrequency paymentFrequency;
  @ManyToOne public Partner contractor;
  @ManyToOne public PartnerServiceType serviceType;

  @Builder
  public ClientContract(
      String fileName,
      Long fileId,
      boolean legacy,
      String contractNumber,
      long paymentValue,
      PaymentFrequency paymentFrequency,
      Partner contractor,
      PartnerServiceType serviceType) {
    super(fileName, fileId);
    this.legacy = legacy;
    this.contractNumber = contractNumber;
    this.paymentValue = paymentValue;
    this.paymentFrequency = paymentFrequency;
    this.contractor = contractor;
    this.serviceType = serviceType;
  }

  public static ClientContract byId(long id) {
    Optional<ClientContract> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
