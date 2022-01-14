package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(orphanRemoval = true)
    public GenericPerson partner;
    @OneToMany(orphanRemoval = true)
    public List<GenericPerson> children;
    @OneToMany(orphanRemoval = true)
    public List<Contract> contracts;
    @OneToMany(orphanRemoval = true)
    public List<ProofOfIdentity> proofOfIdentities;

    public String bank;
    public String iban;
    public String bic;


}
