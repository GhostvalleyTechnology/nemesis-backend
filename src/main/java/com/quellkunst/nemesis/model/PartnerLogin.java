package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class PartnerLogin extends EntityBase {
    public String link;
    public String username;
    public String password;
    public boolean adminOnly;
}
