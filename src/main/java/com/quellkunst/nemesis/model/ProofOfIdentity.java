package com.quellkunst.nemesis.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.notFoundException;

@NoArgsConstructor
@Entity
public class ProofOfIdentity extends FileEntityBase {
  public ProofOfIdentityType type;

  @Builder
  public ProofOfIdentity(String fileName, Long fileId, ProofOfIdentityType type) {
    super(fileName, fileId);
    this.type = type;
  }

  public static ProofOfIdentity byId(long id) {
    Optional<ProofOfIdentity> maybe = findByIdOptional(id);
    return maybe.orElseThrow(notFoundException("Could not find requested resource!"));
  }

  public void removeFromClient() {
    var query =
        String.format(
            "select c from Client c join c.%s poi where poi = ?1", Client_.PROOF_OF_IDENTITIES);
    Client client = Client.find(query, this).firstResult();
    client.proofOfIdentities.remove(this);
  }
}
