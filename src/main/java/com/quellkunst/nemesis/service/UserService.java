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

    var admin = new Employee();
    admin.email = ("admin@quellkunst.com");
    admin.name = ("Admin");
    admin.admin = (true);
    admin.persistAndFlush();

    var emp = new Employee();
    emp.email = ("test@quellkunst.com");
    emp.name = ("Test");
    emp.admin = (false);
    emp.persistAndFlush();

    var spouse = new GenericPerson();
    spouse.firstName = "Eve";
    spouse.lastName = "Paradise";
    spouse.gender = Gender.x;
    spouse.persist();

    var client = new Client();
    client.gender = (Gender.m);
    client.firstName = ("Adam");
    client.lastName = ("Apple Tree");
    client.email = ("adam@heaven.com");
    client.birthday = (LocalDate.of(1990, 9, 1));
    client.nationality = (Country.AT);
    client.address = ("Hell's Highway 5");
    client.zipCode = ("666");
    client.city = ("Garden Eden");
    client.supervisor = (emp);
    client.deleted = (false);
    client.militaryServiceDone = (true);
    client.smoker = (false);
    client.pets = (true);
    client.petsRemarks = ("Two cats");
    client.maritalStatus = (MaritalStatus.married);
    client.homeRemarks = ("Rent 55qm");
    client.partner = (spouse);
    client.persist();

    var kfz = new PartnerServiceType();
    kfz.service = "KFZ";
    kfz.persist();
    var lv = new PartnerServiceType();
    lv.service = "LV";
    lv.persist();
    var services = new TreeSet<PartnerServiceType>();
    services.add(kfz);
    services.add(lv);
    var generali = new Partner();
    generali.name = "Generali";
    generali.services = services;
    generali.persist();

    var muki = new Partner();
    muki.name = "Muki";
    muki.services = services;
    muki.persist();
    var cloudFile = new CloudFile();
    cloudFile.objectName = "06e25032-3280-46b0-a8a2-d6e06ebf09cb";
    cloudFile.fileName = "logo.png";
    cloudFile.fileExtension = "image/png";

    var template = new Template();
    template.adminOnly = false;
    template.file = cloudFile;
    template.persist();
  }
}
