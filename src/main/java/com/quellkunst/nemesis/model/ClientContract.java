package com.quellkunst.nemesis.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

  @AttributeOverrides({
    @AttributeOverride(name = "objectName", column = @Column(name = "policy_request_object_name")),
    @AttributeOverride(name = "fileName", column = @Column(name = "policy_request_file_name")),
    @AttributeOverride(
        name = "fileExtension",
        column = @Column(name = "policy_request_file_extension"))
  })
  @Embedded
  public CloudFile policyRequest;

  @AttributeOverrides({
    @AttributeOverride(name = "objectName", column = @Column(name = "policy_object_name")),
    @AttributeOverride(name = "fileName", column = @Column(name = "policy_file_name")),
    @AttributeOverride(name = "fileExtension", column = @Column(name = "policy_file_extension"))
  })
  @Embedded
  public CloudFile policy;
}
