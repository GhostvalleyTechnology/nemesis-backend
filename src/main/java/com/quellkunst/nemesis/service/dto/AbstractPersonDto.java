package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.Country;
import com.quellkunst.nemesis.model.Gender;
import com.quellkunst.nemesis.model.Person;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public abstract class AbstractPersonDto<T extends Person> extends AbstractEntityDto<T> {
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

  public AbstractPersonDto(T entity) {
    super(entity);
  }

  protected static <T extends Person, D extends AbstractPersonDto<?>> void mapEntityToDto(
      D dto, T entity) {
    dto.gender = entity.gender;
    dto.firstName = entity.firstName;
    dto.lastName = entity.lastName;
    dto.email = entity.email;
    dto.phone = entity.phone;
    dto.mobile = entity.mobile;
    dto.title = entity.title;
    dto.academicDegree = entity.academicDegree;
    dto.birthday = entity.birthday;
    dto.birthPlace = entity.birthPlace;
    dto.nationality = entity.nationality;
    dto.socialInsuranceInstitution = entity.socialInsuranceInstitution;
    dto.occupation = entity.occupation;
    dto.country = entity.country;
    dto.zipCode = entity.zipCode;
    dto.city = entity.city;
    dto.address = entity.address;
  }

  protected T mapPersonValues(T entity) {
    entity.gender = gender;
    entity.firstName = firstName;
    entity.lastName = lastName;
    entity.email = email;
    entity.phone = phone;
    entity.mobile = mobile;
    entity.title = title;
    entity.academicDegree = academicDegree;
    entity.birthday = birthday;
    entity.birthPlace = birthPlace;
    entity.nationality = nationality;
    entity.socialInsuranceInstitution = socialInsuranceInstitution;
    entity.occupation = occupation;
    entity.country = country;
    entity.zipCode = zipCode;
    entity.city = city;
    entity.address = address;
    return entity;
  }
}
