package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ReminderType;
import java.time.LocalDate;

public class ReminderDto {
  public ReminderType type;
  public String name;
  public String text;
  public LocalDate due;
  public boolean done;
  public boolean sent;
  public long clientId;
  public GenericPersonDto personDto;
}
