package com.quellkunst.nemesis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@AttributeOverrides({
  @AttributeOverride(name = "name", column = @Column(name = "google_object_name"))
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleFile {
  String name;
}