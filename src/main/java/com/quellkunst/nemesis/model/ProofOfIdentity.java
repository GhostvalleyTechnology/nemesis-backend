package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProofOfIdentity extends EntityBase {
  public ProofOfIdentityType type;
  @Embedded public GoogleFile file;

  public void removeFromClient() {
    var query =
        String.format(
            "select c from Client c join c.%s poi where poi = ?1", Client_.PROOF_OF_IDENTITIES);
    Client client = Client.find(query, this).firstResult();
    client.proofOfIdentities.remove(this);
  }
}