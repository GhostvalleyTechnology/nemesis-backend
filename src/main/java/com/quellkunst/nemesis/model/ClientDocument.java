package com.quellkunst.nemesis.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ClientDocument extends EntityBase {
  @ManyToOne public Client client;
  public ClientDocumentType type;
  @Embedded public CloudFile file;
}
