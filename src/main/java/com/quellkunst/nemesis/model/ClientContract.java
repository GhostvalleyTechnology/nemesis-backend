package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientContract extends EntityBase {
  @ManyToOne(fetch = FetchType.LAZY)
  public Client client;

  public boolean legacy;
  public String contractNumber;
  public long paymentValue;
  public PaymentFrequency paymentFrequency;
  public LocalDate contractDate;

  @ManyToOne(fetch = FetchType.LAZY)
  public Partner contractor;

  @ManyToOne(fetch = FetchType.LAZY)
  public PartnerServiceType serviceType;

  @AttributeOverrides({
    @AttributeOverride(
        name = CloudFile_.OBJECT_NAME,
        column = @Column(name = "policy_request_object_name")),
    @AttributeOverride(
        name = CloudFile_.FILE_NAME,
        column = @Column(name = "policy_request_file_name")),
    @AttributeOverride(
        name = CloudFile_.FILE_EXTENSION,
        column = @Column(name = "policy_request_file_extension"))
  })
  @Embedded
  public CloudFile policyRequest;

  @AttributeOverrides({
    @AttributeOverride(
        name = CloudFile_.OBJECT_NAME,
        column = @Column(name = "policy_object_name")),
    @AttributeOverride(name = CloudFile_.FILE_NAME, column = @Column(name = "policy_file_name")),
    @AttributeOverride(
        name = CloudFile_.FILE_EXTENSION,
        column = @Column(name = "policy_file_extension"))
  })
  @Embedded
  public CloudFile policy;
}
