package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@NoArgsConstructor
@Entity
public class Partner extends EntityBase {
    public String name;
    public String website;
    public String bank;
    public String iban;
    public String bic;
    @OneToMany
    public List<PartnerContact> contacts;
    @OneToMany
    public List<PartnerLogin> logins;
    @OneToMany
    public List<PartnerServiceType> services;
}
