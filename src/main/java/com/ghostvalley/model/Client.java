package com.ghostvalley.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Client extends Person {
    @ManyToOne(optional = false)
    public Employee supervisor;
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
