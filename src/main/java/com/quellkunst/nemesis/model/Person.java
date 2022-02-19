package com.quellkunst.nemesis.model;

import java.time.LocalDate;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person extends EntityBase {
  public Gender gender;
  public String firstName;
  public String lastName;

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
}
