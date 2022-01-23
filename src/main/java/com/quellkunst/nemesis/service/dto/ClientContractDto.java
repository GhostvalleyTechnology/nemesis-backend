package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.PaymentFrequency;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

public class ClientContractDto extends AbstractFileBasedDto {
  @FormParam("clientId")
  @PartType(MediaType.TEXT_PLAIN)
  public long clientId;

  @FormParam("legacy")
  @PartType(MediaType.TEXT_PLAIN)
  public boolean legacy;

  @FormParam("contractNumber")
  @PartType(MediaType.TEXT_PLAIN)
  public String contractNumber;

  @FormParam("paymentValue")
  @PartType(MediaType.TEXT_PLAIN)
  public long paymentValue;

  @FormParam("paymentFrequency")
  @PartType(MediaType.TEXT_PLAIN)
  public PaymentFrequency paymentFrequency;

  @FormParam("contractorId")
  @PartType(MediaType.TEXT_PLAIN)
  public long contractorId;
}
