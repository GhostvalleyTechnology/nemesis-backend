package com.quellkunst.nemesis.model;

import java.util.List;
import java.util.SortedSet;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.SortNatural;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Partner extends EntityBase {
  @Column(unique = true)
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
}
