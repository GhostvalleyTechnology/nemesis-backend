package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client extends Person {
  @ManyToOne(optional = false)
  public Employee supervisor;

  @Column(nullable = false)
  public boolean deleted;

  public Boolean militaryServiceDone;
  public Boolean smoker;
  public Boolean pets;
  public String petsRemarks;
  public MaritalStatus maritalStatus;
  public String homeRemarks;

  @OneToOne(orphanRemoval = true)
  public GenericPerson partner;

  @OneToMany(orphanRemoval = true)
  public List<GenericPerson> children;

  @OneToMany(orphanRemoval = true)
  public List<ClientContract> clientContracts;

  @OneToMany(orphanRemoval = true)
  public List<ClientDocument> clientDocuments;

  @OneToMany(orphanRemoval = true)
  public List<ProofOfIdentity> proofOfIdentities;

  public String bank;
  public String iban;
  public String bic;
}