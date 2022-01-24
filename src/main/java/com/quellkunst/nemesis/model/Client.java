package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@Entity
@NoArgsConstructor
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
  public List<ProofOfIdentity> proofOfIdentities;

  public String bank;
  public String iban;
  public String bic;

  public static Client byId(long id) {
    Optional<Client> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
