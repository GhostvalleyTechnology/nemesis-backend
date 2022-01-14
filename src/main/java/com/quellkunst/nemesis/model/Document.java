package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
public class Document extends EntityBase {

    public String name;
    @Lob
    @Basic(fetch=LAZY)
    public byte[] data;

    @Builder
    public Document(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
