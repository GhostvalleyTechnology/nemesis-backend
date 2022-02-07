package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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

  @Builder
  public Client(
      Gender gender,
      String firstName,
      String lastName,
      String email,
      String phone,
      String mobile,
      String title,
      String academicDegree,
      LocalDate birthday,
      String birthPlace,
      Country nationality,
      String socialInsuranceInstitution,
      String occupation,
      Country country,
      String zipCode,
      String city,
      String address,
      Employee supervisor,
      boolean deleted,
      Boolean militaryServiceDone,
      Boolean smoker,
      Boolean pets,
      String petsRemarks,
      MaritalStatus maritalStatus,
      String homeRemarks,
      GenericPerson partner,
      List<GenericPerson> children,
      List<ClientContract> clientContracts,
      List<ClientDocument> clientDocuments,
      List<ProofOfIdentity> proofOfIdentities,
      String bank,
      String iban,
      String bic) {
    super(
        gender,
        firstName,
        lastName,
        email,
        phone,
        mobile,
        title,
        academicDegree,
        birthday,
        birthPlace,
        nationality,
        socialInsuranceInstitution,
        occupation,
        country,
        zipCode,
        city,
        address);
    this.supervisor = supervisor;
    this.deleted = deleted;
    this.militaryServiceDone = militaryServiceDone;
    this.smoker = smoker;
    this.pets = pets;
    this.petsRemarks = petsRemarks;
    this.maritalStatus = maritalStatus;
    this.homeRemarks = homeRemarks;
    this.partner = partner;
    this.children = children;
    this.clientContracts = clientContracts;
    this.clientDocuments = clientDocuments;
    this.proofOfIdentities = proofOfIdentities;
    this.bank = bank;
    this.iban = iban;
    this.bic = bic;
  }
}
