package com.quellkunst.nemesis.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends Person {
  @ManyToOne(optional = false)
  public Employee supervisor;

  public boolean deleted;

  public boolean militaryServiceDone;
  public boolean smoker;
  public boolean pets;
  public String petsRemarks;
  public MaritalStatus maritalStatus;
  public String homeRemarks;

  @OneToOne(orphanRemoval = true)
  public GenericPerson partner;

  @OneToMany(orphanRemoval = true)
  public List<GenericPerson> children;

  @OneToMany(orphanRemoval = true)
  public List<ClientContract> contracts;

  @OneToMany(orphanRemoval = true)
  public List<ClientDocument> documents;

  @OneToMany(orphanRemoval = true)
  public List<ProofOfIdentity> proofOfIdentities;

  public String bank;
  public String iban;
  public String bic;
}
