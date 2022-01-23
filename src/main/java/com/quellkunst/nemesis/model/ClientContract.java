package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Entity
public class ClientContract extends FileEntityBase {
  public boolean legacy;
  public String contractNumber;
  public long paymentValue;
  public PaymentFrequency paymentFrequency;
  @ManyToOne public Partner contractor;

  @Builder
  public ClientContract(
      String fileName,
      Long fileId,
      boolean legacy,
      String contractNumber,
      long paymentValue,
      PaymentFrequency paymentFrequency,
      Partner contractor) {
    super(fileName, fileId);
    this.legacy = legacy;
    this.contractNumber = contractNumber;
    this.paymentValue = paymentValue;
    this.paymentFrequency = paymentFrequency;
    this.contractor = contractor;
  }
}
