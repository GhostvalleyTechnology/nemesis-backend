package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ClientDocument extends EntityBase {
  @ManyToOne public Client client;
  public ClientDocumentType type;
  @Embedded GoogleFile file;
}