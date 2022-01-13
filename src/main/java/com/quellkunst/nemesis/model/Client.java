package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Client extends Person {
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence")
            }
    )
    public long clientId;
    @ManyToOne(optional = false)
    public Employee supervisor;

    public String title;
    public String academicDegree;
    public LocalDate birthday;
    public String birthPlace;
    public Country nationality;
    public String socialInsuranceInstitution;
    public String occupation;
    public boolean militaryServiceDone;
    public boolean smoker;
    public boolean pets;
    public boolean petsRemarks;
    public MaritalStatus maritalStatus;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    public Person partner;
    @OneToMany(orphanRemoval = true)
    public List<Person> children;

    public String bank;
    public String iban;
    public String bic;

    @Builder
    public Client(Gender gender, String firstName, String lastName, String title, String academicDegree, LocalDate birthday, String occupation, LocalDateTime createdAt, String email, String phone, String mobile, Country country, String zipCode, String city, String address, Employee supervisor, String bank, String iban, String bic) {
        super(gender, firstName, lastName, title, academicDegree, birthday, occupation, createdAt, email, phone, mobile, country, zipCode, city, address);
        this.supervisor = supervisor;
        this.bank = bank;
        this.iban = iban;
        this.bic = bic;
    }
}
