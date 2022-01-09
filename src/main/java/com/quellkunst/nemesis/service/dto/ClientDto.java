package com.quellkunst.nemesis.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {
    private String bank;
    private String iban;
    private String bic;
}
