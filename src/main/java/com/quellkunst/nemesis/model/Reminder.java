package com.quellkunst.nemesis.model;

import io.quarkus.mailer.Mail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reminder extends EntityBase {
  @ManyToOne public Employee employee;

  @Column(nullable = false)
  public ReminderType type;

  public String name;
  public String text;
  public LocalDate due;
  @ManyToOne public Client client;
  public boolean done;
  public boolean sent;

  public static void createNewClientReminders(Client client) {
    Reminder.builder()
        .employee(client.supervisor)
        .type(ReminderType.service)
        .name("Polizzenservice")
        .text(String.format("Das Service für %s %s ist fällig!", client.firstName, client.lastName))
        .due(LocalDate.now().plusWeeks(3))
        .build()
        .persist();
    Reminder.builder()
        .employee(client.supervisor)
        .type(ReminderType.service)
        .name("Jahresservice")
        .text(String.format("Das Service für %s %s ist fällig!", client.firstName, client.lastName))
        .due(LocalDate.now().plusYears(1))
        .build()
        .persist();
  }

  public static List<Reminder> getDueToday() {
    return Reminder.list(
        "select r from Reminder r where r.done = false and r.due = ?1", LocalDate.now());
  }

  @Transient
  public void sent() {
    Reminder.update("sent = true where id = ?1", id);
  }

  public Mail getMail() {
    return Mail.withText(employee.email, name, text);
  }
}