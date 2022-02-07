package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class GenericPerson extends Person {
  @Builder
  public GenericPerson(
      Gender gender,
      String firstName,
      String lastName,
      String email,
      String phone,
      String mobile,
      String title,
      String academicDegree,
      LocalDate birthday,
      String birthPlace,
      Country nationality,
      String socialInsuranceInstitution,
      String occupation,
      Country country,
      String zipCode,
      String city,
      String address) {
    super(
        gender,
        firstName,
        lastName,
        email,
        phone,
        mobile,
        title,
        academicDegree,
        birthday,
        birthPlace,
        nationality,
        socialInsuranceInstitution,
        occupation,
        country,
        zipCode,
        city,
        address);
  }
}
