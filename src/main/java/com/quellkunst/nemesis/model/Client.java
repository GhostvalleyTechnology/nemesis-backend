package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    public List<Document> documents;

    public String bank;
    public String iban;
    public String bic;

    @Builder
    public Client(Gender gender, String firstName, String lastName, String email, String phone, String mobile, String title, String academicDegree, LocalDate birthday, String birthPlace, Country nationality, String socialInsuranceInstitution, String occupation, Country country, String zipCode, String city, String address, Employee supervisor, boolean militaryServiceDone, boolean smoker, boolean pets, String petsRemarks, MaritalStatus maritalStatus, GenericPerson partner, List<GenericPerson> children, String bank, String iban, String bic) {
        super(gender, firstName, lastName, email, phone, mobile, title, academicDegree, birthday, birthPlace, nationality, socialInsuranceInstitution, occupation, country, zipCode, city, address);
        this.supervisor = supervisor;
        this.militaryServiceDone = militaryServiceDone;
        this.smoker = smoker;
        this.pets = pets;
        this.petsRemarks = petsRemarks;
        this.maritalStatus = maritalStatus;
        this.partner = partner;
        this.children = children;
        this.bank = bank;
        this.iban = iban;
        this.bic = bic;
    }
}
