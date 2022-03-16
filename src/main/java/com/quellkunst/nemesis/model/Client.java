package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client extends Person {

  @Generated(GenerationTime.INSERT)
  @Column(columnDefinition = "serial", updatable = false)
  public Long clientNumber;

  @ManyToOne(optional = false)
  public Employee supervisor;

  public boolean deleted;

  public boolean militaryServiceDone;
  public boolean smoker;
  public boolean pets;
  public String petsRemarks;
  public MaritalStatus maritalStatus;
  public String homeRemarks;

  @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
  public GenericPerson partner;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "client_id")
  public List<GenericPerson> children = new ArrayList<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = ClientContract_.CLIENT)
  public List<ClientContract> contracts = new ArrayList<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = ClientDocument_.CLIENT)
  public List<ClientDocument> documents = new ArrayList<>();

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = ProofOfIdentity_.CLIENT)
  public List<ProofOfIdentity> proofOfIdentities = new ArrayList<>();

  public String bank;
  public String iban;
  public String bic;

  public void addContract(ClientContract contract) {
    contracts.add(contract);
    contract.client = this;
  }

  public void removeContract(ClientContract contract) {
    contracts.remove(contract);
    contract.client = null;
  }

  public void addDocument(ClientDocument document) {
    documents.add(document);
    document.client = this;
  }

  public void removeDocument(ClientDocument document) {
    documents.remove(document);
    document.client = null;
  }

  public void addProofOfIdentity(ProofOfIdentity proof) {
    proofOfIdentities.add(proof);
    proof.client = this;
  }

  public void removeProofOfIdentity(ProofOfIdentity proof) {
    proofOfIdentities.remove(proof);
    proof.client = null;
  }
}
