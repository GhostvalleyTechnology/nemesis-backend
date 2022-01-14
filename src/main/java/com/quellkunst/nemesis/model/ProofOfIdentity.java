package com.quellkunst.nemesis.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class ProofOfIdentity extends FileEntityBase {
    public ProofOfIdentityType type;
}
