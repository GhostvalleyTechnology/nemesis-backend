package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class PartnerContact extends EntityBase {
    public String name;
    public String email;
    public String phone;
    public String remarks;
}
