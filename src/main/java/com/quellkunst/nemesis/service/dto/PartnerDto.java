package com.quellkunst.nemesis.service.dto;

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
public class PartnerDto extends PartnerReferenceDto {
  String website;
  String bank;
  String iban;
  String bic;
  List<PartnerLoginDto> logins = Collections.emptyList();
  List<PartnerContactDto> contacts = Collections.emptyList();
  List<PartnerIntermediaryNumberDto> intermediaryNumbers = Collections.emptyList();
}
