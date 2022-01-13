package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@MappedSuperclass
@Entity
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

    public Country country;
    public String zipCode;
    public String city;
    public String address;


}
