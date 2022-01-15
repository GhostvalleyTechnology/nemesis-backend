package com.quellkunst.nemesis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.security.Context;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

public abstract class TestBase {
  @InjectMock Context context;

  public void testAsAdmin() {
    when(context.getEmail()).thenReturn("admin@quellkunst.com");
    doCallRealMethod().when(context).getCurrentEmployee();
  }

  public void testAsUser() {
    when(context.getEmail()).thenReturn("test@quellkunst.com");
    doCallRealMethod().when(context).getCurrentEmployee();
  }

  public static RequestSpecification givenJson(Object object) {
    var objectMapper = new ObjectMapper();
    try {
      var json = objectMapper.writeValueAsString(object);
      return given().contentType(ContentType.JSON).body(json);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  public static Employee admin() {
    var emp = new Employee();
    emp.admin = true;
    emp.email = "admin@quellkunst.com";
    emp.name = "Admin";
    return emp;
  }

  public static Employee testUser() {
    var emp = new Employee();
    emp.admin = false;
    emp.email = "test@quellkunst.com";
    emp.name = "Test User";
    return emp;
  }
}
