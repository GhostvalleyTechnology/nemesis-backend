package com.quellkunst.nemesis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quellkunst.nemesis.model.Employee;
import com.quellkunst.nemesis.model.EntityBase;
import com.quellkunst.nemesis.security.AppContext;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

public abstract class TestBase {
  @InjectMock
  AppContext context;
  @Inject SessionFactory sessionFactory;

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

  public void authAsAdmin() {
    when(context.getEmail()).thenReturn("admin@quellkunst.com");
    doCallRealMethod().when(context).getCurrentEmployee();
  }

  public void authAsUser() {
    when(context.getEmail()).thenReturn("test@quellkunst.com");
    doCallRealMethod().when(context).getCurrentEmployee();
  }

  /**
   * String has to start with "http:" may not contain "api", must end with the given pathPart
   * followed by / and a number
   *
   * @param pathPart of the service
   * @return the matcher containing the regex
   */
  Matcher<String> createdLocation(String pathPart) {
    return Matchers.matchesRegex("^http:(?!.*api).*" + pathPart + "/\\d*$");
  }

  @SafeVarargs
  @Transactional
  public final void clearTable(Class<? extends EntityBase>... entities) {
    try (var session = sessionFactory.openSession()) {
      for (var entity : entities) {
        session.createQuery("delete from " + entity.getSimpleName()).executeUpdate();
      }
    }
  }
}
