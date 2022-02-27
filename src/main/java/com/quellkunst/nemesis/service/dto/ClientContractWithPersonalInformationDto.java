package com.quellkunst.nemesis.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientContractWithPersonalInformationDto {
  private ClientContractDto contractDto;
  private GenericPersonDto clientDto;
}
