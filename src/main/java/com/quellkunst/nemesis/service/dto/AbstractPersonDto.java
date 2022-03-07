package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Country;
import com.quellkunst.nemesis.model.Gender;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public abstract class AbstractPersonDto extends AbstractEntityDto {
  Gender gender;
  String firstName;
  String lastName;
  String email;
  String phone;
  String mobile;
  String title;
  String academicDegree;
  LocalDate birthday;
  String birthPlace;
  Country nationality;
  String socialInsuranceInstitution;
  String occupation;
  Country country;
  String zipCode;
  String city;
  String address;
}
