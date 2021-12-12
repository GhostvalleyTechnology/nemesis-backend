package com.ghostvalley.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Country extends EntityBase {
    public String name;
    public String alpha2;

    @Builder
    public Country(String name, String alpha2) {
        this.name = name;
        this.alpha2 = alpha2;
    }
}
