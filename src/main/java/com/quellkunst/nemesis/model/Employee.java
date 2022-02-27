package com.quellkunst.nemesis.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee extends EntityBase {
  @Column(unique = true)
  public String email;

  public String name;
  public boolean admin;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = Reminder_.EMPLOYEE)
  public List<Reminder> reminder = new ArrayList<>();

  public void addReminder(Reminder r) {
    reminder.add(r);
    r.employee = this;
  }

  public void removeReminder(Reminder r) {
    reminder.remove(r);
    r.employee = null;
  }
}
