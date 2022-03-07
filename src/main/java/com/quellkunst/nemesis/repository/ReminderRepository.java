package com.quellkunst.nemesis.repository;

import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.model.Reminder_;
import com.quellkunst.nemesis.security.AppContext;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ReminderRepository extends BaseRepository<Reminder> {
  @Inject AppContext context;

  public Stream<Reminder> getEmployeeReminders() {
    return stream(Reminder_.EMPLOYEE, context.getCurrentEmployee());
  }
}
