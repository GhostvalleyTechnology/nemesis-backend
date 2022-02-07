package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ClientContract extends EntityBase {
  @ManyToOne public Client client;
  public boolean legacy;
  public String contractNumber;
  public long paymentValue;
  public PaymentFrequency paymentFrequency;
  @ManyToOne public Partner contractor;
  @ManyToOne public PartnerServiceType serviceType;
  @Embedded public GoogleFile policyRequest;
  @Embedded public GoogleFile policy;
}