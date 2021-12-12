package com.ghostvalley.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
public class Employee extends Person {
    @OneToMany
    public List<Client> clients;

    @Builder
    public Employee(Gender gender, String firstName, String lastName, String title, String academicDegree, LocalDate birthday, String occupation, LocalDateTime createdAt, String email, String phone, String mobile, Country country, String zipCode, String city, String address, List<Client> clients) {
        super(gender, firstName, lastName, title, academicDegree, birthday, occupation, createdAt, email, phone, mobile, country, zipCode, city, address);
        this.clients = clients;
    }
}
