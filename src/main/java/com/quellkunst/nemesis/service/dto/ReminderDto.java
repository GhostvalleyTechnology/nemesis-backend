package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ReminderType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ReminderDto {
  ReminderType type;
  String name;
  String text;
  LocalDate due;
  boolean done;
  boolean sent;
  ClientReferenceDto client;
}
