package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class Partner extends EntityBase {
  public String name;
  public String website;
  public String bank;
  public String iban;
  public String bic;

  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<PartnerContact> contacts;

  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  public List<PartnerLogin> logins;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "partner_service",
      joinColumns = @JoinColumn(name = "partner_id"),
      inverseJoinColumns = @JoinColumn(name = "service_id"))
  @SortNatural
  @LazyCollection(LazyCollectionOption.FALSE)
  public SortedSet<PartnerServiceType> services;

  @Builder
  public Partner(
      String name,
      String website,
      String bank,
      String iban,
      String bic,
      List<PartnerContact> contacts,
      List<PartnerLogin> logins,
      SortedSet<PartnerServiceType> services) {
    this.name = name;
    this.website = website;
    this.bank = bank;
    this.iban = iban;
    this.bic = bic;
    this.contacts = contacts;
    this.logins = logins;
    this.services = services;
  }

  public static Partner byId(long id) {
    Optional<Partner> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }
}
