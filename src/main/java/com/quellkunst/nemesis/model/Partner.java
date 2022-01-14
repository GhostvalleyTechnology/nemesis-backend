package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@NoArgsConstructor
@Entity
public class Partner extends EntityBase {
    public String name;
    public String website;
    public String bank;
    public String iban;
    public String bic;
    public List<PartnerContact> contacts;
    public List<PartnerLogin> logins;
    public List<PartnerServiceType> services;
}
