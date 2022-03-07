package com.quellkunst.nemesis.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProofOfIdentity extends EntityBase {
  @ManyToOne(fetch = FetchType.LAZY)
  public Client client;

  public ProofOfIdentityType type;
  @Embedded public CloudFile file;

  public void removeFromClient() {
    var query =
        String.format(
            "select c from Client c join c.%s poi where poi = ?1", Client_.PROOF_OF_IDENTITIES);
    Client client = Client.find(query, this).firstResult();
    client.proofOfIdentities.remove(this);
  }
}
