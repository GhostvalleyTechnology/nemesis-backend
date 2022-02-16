package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.MaritalStatus;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ClientDto extends AbstractPersonDto {
  String supervisor;
  boolean deleted;
  Boolean militaryServiceDone;
  Boolean smoker;
  Boolean pets;
  String petsRemarks;
  MaritalStatus maritalStatus;
  String homeRemarks;
  String bank;
  String iban;
  String bic;
  GenericPersonDto partner;
  List<GenericPersonDto> children;
  List<ClientContractDto> contracts = Collections.emptyList();
  List<ClientDocumentDto> documents = Collections.emptyList();
  List<ProofOfIdentityDto> proofOfIdentities = Collections.emptyList();
}
