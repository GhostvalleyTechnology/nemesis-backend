package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
public class File extends EntityBase {
    @Lob
    @Basic(fetch = LAZY)
    public byte[] data;

    @Builder
    public File(byte[] data) {
        this.data = data;
    }
}
