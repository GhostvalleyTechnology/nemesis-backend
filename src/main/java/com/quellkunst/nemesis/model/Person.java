package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.Builder;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public class Person extends EntityBase {
    @Column(nullable = false)
    public Gender gender;
    @Column(nullable = false)
    public String firstName;
    @Column(nullable = false)
    public String lastName;

    @Column(unique = true)
    public String email;
    public String phone;
    public String mobile;

    public String title;
    public String academicDegree;
    public LocalDate birthday;
    public String birthPlace;
    public Country nationality;
    public String socialInsuranceInstitution;
    public String occupation;

    public Country country;
    public String zipCode;
    public String city;
    public String address;

    public Person(Gender gender, String firstName, String lastName, String email, String phone, String mobile, String title, String academicDegree, LocalDate birthday, String birthPlace, Country nationality, String socialInsuranceInstitution, String occupation, Country country, String zipCode, String city, String address) {
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.title = title;
        this.academicDegree = academicDegree;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.nationality = nationality;
        this.socialInsuranceInstitution = socialInsuranceInstitution;
        this.occupation = occupation;
        this.country = country;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
    }
}
