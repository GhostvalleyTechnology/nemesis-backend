package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartnerIntermediaryNumber extends EntityBase {
    public String description;
    public String number;
}
