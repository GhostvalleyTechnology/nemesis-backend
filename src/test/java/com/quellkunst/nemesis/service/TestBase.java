package com.quellkunst.nemesis.service;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quellkunst.nemesis.model.EntityBase;
import com.quellkunst.nemesis.security.AppContext;
import com.quellkunst.nemesis.service.dto.EmployeeDto;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;

public abstract class TestBase {
  @InjectMock AppContext context;
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

  public static EmployeeDto admin() {
    var emp = new EmployeeDto();
    emp.setAdmin(true);
    emp.setEmail("admin@quellkunst.com");
    emp.setName("Admin");
    return emp;
  }

  public static EmployeeDto testUser() {
    var emp = new EmployeeDto();
    emp.setAdmin(false);
    emp.setEmail("test@quellkunst.com");
    emp.setName("Test User");
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
