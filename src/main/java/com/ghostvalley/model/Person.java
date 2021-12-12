package com.ghostvalley.model;

import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
public abstract class Person extends EntityBase {
    public Gender gender;
    public String firstName;
    public String lastName;
    public String title;
    public String academicDegree;
    public LocalDate birthday;
    public String occupation;
    public LocalDateTime createdAt;

    public String email;
    public String phone;
    public String mobile;

    @ManyToOne
    public Country country;
    public String zipCode;
    public String city;
    public String address;

    public Person(Gender gender, String firstName, String lastName, String title, String academicDegree, LocalDate birthday, String occupation, LocalDateTime createdAt, String email, String phone, String mobile, Country country, String zipCode, String city, String address) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.academicDegree = academicDegree;
        this.birthday = birthday;
        this.occupation = occupation;
        this.createdAt = createdAt;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
    }
}
