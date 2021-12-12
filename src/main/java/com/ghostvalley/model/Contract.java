package com.ghostvalley.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Contract extends EntityBase {
    public String link;

    @Builder
    public Contract(String link) {
        this.link = link;
    }
}
