package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Employee;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class EmployeeServiceTest extends TestBase {
  @AfterEach
  public void clearTables() {
    clearTable(Employee.class);
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testAddEmployees() {
    authAsAdmin();
    givenJson(admin())
        .post("/api/employee/add")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .header("Location", createdLocation("/employee"));
    givenJson(testUser())
        .post("/api/employee/add")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .header("Location", createdLocation("/employee"));
    given()
        .get("/api/employee/list")
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body(containsString("admin@quellkunst.com"))
        .body(containsString("test@quellkunst.com"));
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testAddEmployeeAsUser() {
    authAsAdmin();
    givenJson(testUser())
        .post("/api/employee/add")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode())
        .header("Location", createdLocation("/employee"));
    authAsUser();
    givenJson(admin())
        .post("/api/employee/add")
        .then()
        .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testUpdateEmployee() {
    authAsAdmin();
    givenJson(testUser())
        .post("/api/employee/add")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode());
    var resultBody = given().get("/api/employee/list").then().extract().body().asString();
    var newJsonRequest =
        resultBody.substring(1, resultBody.length() - 1).replace(testUser().getName(), "New Name!");
    given()
        .contentType(ContentType.JSON)
        .body(newJsonRequest)
        .post("/api/employee/update")
        .then()
        .statusCode(Response.Status.OK.getStatusCode());
    given()
        .get("/api/employee/list")
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .body(containsString("New Name!"));
  }
}
