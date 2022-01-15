package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Entity
public class Contract extends FileEntityBase {
  public boolean old;
  public String contractNumber;
  public PaymentFrequency paymentFrequency;
  @ManyToOne public Partner contractor;
}
