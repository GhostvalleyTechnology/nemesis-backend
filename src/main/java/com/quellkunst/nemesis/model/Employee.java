package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.illegalCallerException;

@Entity
@NoArgsConstructor
@Getter
public class Employee extends Person {
    @OneToMany
    public List<Client> clients;
    public boolean adminRights;

    @Builder
    public Employee(Gender gender, String firstName, String lastName, String title, String academicDegree, LocalDate birthday, String occupation, LocalDateTime createdAt, String email, String phone, String mobile, Country country, String zipCode, String city, String address, List<Client> clients, boolean adminRights) {
        super(gender, firstName, lastName, title, academicDegree, birthday, occupation, createdAt, email, phone, mobile, country, zipCode, city, address);
        this.clients = clients;
        this.adminRights = adminRights;
    }

    public static Optional<Employee> findByEmail(String email) {
        return find(Employee_.EMAIL, email).firstResultOptional();
    }

    public static Employee getByEmail(String email) {
        return findByEmail(email).orElseThrow(illegalCallerException("E-Mail '" + email + "' not found."));
    }
}
