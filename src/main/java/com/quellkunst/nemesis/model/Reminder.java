package com.quellkunst.nemesis.model;

import io.quarkus.mailer.Mail;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reminder extends EntityBase {
  @ManyToOne(optional = false)
  public Employee employee;

  @Column(nullable = false)
  public ReminderType type;

  public String name;
  public String text;
  public LocalDate due;
  @ManyToOne public Client client;
  public boolean done;
  public boolean sent;

  public static void createNewClientReminders(Client client) {
    var policyService = new Reminder();
    policyService.type = ReminderType.service;
    policyService.name = "Polizzenservice";
    policyService.text =
        String.format("Das Service für %s %s ist fällig!", client.firstName, client.lastName);
    policyService.due = LocalDate.now().plusWeeks(3);
    client.supervisor.addReminder(policyService);
    var annualService = new Reminder();
    annualService.type = ReminderType.service;
    annualService.name = "Jahresservice";
    annualService.text =
        String.format("Das Service für %s %s ist fällig!", client.firstName, client.lastName);
    annualService.due = LocalDate.now().plusYears(1);
    client.supervisor.addReminder(annualService);
    client.supervisor.persist();
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
