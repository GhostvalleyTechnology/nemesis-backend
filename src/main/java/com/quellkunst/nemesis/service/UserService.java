package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.mapper.EmployeeMapper;
import com.quellkunst.nemesis.model.*;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.service.dto.EmployeeDto;
import io.quarkus.runtime.LaunchMode;
import java.time.LocalDate;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Transactional
@Path("/me")
public class UserService {

  @Inject AppContext context;
  @Inject EmployeeMapper employeeMapper;

  @GET
  public EmployeeDto get() {
    if (LaunchMode.current().isDevOrTest()) {
      setup();
    }
    return employeeMapper.toDto(context.getCurrentEmployee());
  }

  private void setup() {
    if (Employee.findByEmail("admin@quellkunst.com").isPresent()) {
      return;
    }

    Employee.builder()
        .email("admin@quellkunst.com")
        .name("Admin")
        .admin(true)
        .build()
        .persistAndFlush();
    var emp = Employee.builder().email("test@quellkunst.com").name("Test").admin(false).build();
    emp.persistAndFlush();

    var spouse = new GenericPerson();
    spouse.firstName = "Eve";
    spouse.lastName = "Paradise";
    spouse.gender = Gender.x;
    spouse.persist();

    var client =
        Client.builder()
            .gender(Gender.m)
            .firstName("Adam")
            .lastName("Apple Tree")
            .email("adam@heaven.com")
            .birthday(LocalDate.of(1990, 9, 1))
            .nationality(Country.AT)
            .address("Hell's Highway 5")
            .zipCode("666")
            .city("Garden Eden")
            .supervisor(emp)
            .deleted(false)
            .militaryServiceDone(true)
            .smoker(false)
            .pets(true)
            .petsRemarks("Two cats")
            .maritalStatus(MaritalStatus.married)
            .homeRemarks("Rent 55qm")
            .partner(spouse)
            .build();
    client.persist();

    var kfz = PartnerServiceType.builder().service("KFZ").build();
    kfz.persist();
    var lv = PartnerServiceType.builder().service("LV").build();
    lv.persist();
    var services = new TreeSet<PartnerServiceType>();
    services.add(kfz);
    services.add(lv);
    Partner.builder().name("Generali").services(services).build().persist();

    Partner.builder().name("Muki").services(services).build().persist();

    Template.builder()
        .adminOnly(false)
        .file(
            CloudFile.builder()
                .objectName("06e25032-3280-46b0-a8a2-d6e06ebf09cb")
                .fileName("logo.png")
                .fileExtension("image/png")
                .build())
        .build()
        .persist();
  }
}
