package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
public class Document extends EntityBase {
    public String link;

    @Builder
    public Document(String link) {
        this.link = link;
    }
}
