package com.quellkunst.nemesis.service;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class AdminServiceTest extends TestBase {
  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testAddEmployees() {
    testAsAdmin();
    givenJson(admin())
        .post("/api/admin/add-employee")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode());
    givenJson(testUser())
        .post("/api/admin/add-employee")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode());
    given()
        .get("/api/admin/list-employees")
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .and()
        .body(containsString("admin@quellkunst.com"))
        .and()
        .body(containsString("test@quellkunst.com"));
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testAddEmployeeAsUser() {
    testAsAdmin();
    givenJson(testUser())
        .post("/api/admin/add-employee")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode());
    testAsUser();
    givenJson(admin())
        .post("/api/admin/add-employee")
        .then()
        .statusCode(Response.Status.UNAUTHORIZED.getStatusCode());
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testUpdateEmployee() {
    testAsAdmin();
    givenJson(testUser())
        .post("/api/admin/add-employee")
        .then()
        .statusCode(Response.Status.CREATED.getStatusCode());
    var resultBody = given().get("/api/admin/list-employees").then().extract().body().asString();
    var newJsonRequest =
        resultBody.substring(1, resultBody.length() - 1).replace(testUser().name, "New Name!");
    given()
        .contentType(ContentType.JSON)
        .body(newJsonRequest)
        .post("/api/admin/update-employee")
        .then()
        .statusCode(Response.Status.OK.getStatusCode());
    given()
        .get("/api/admin/list-employees")
        .then()
        .statusCode(Response.Status.OK.getStatusCode())
        .and()
        .body(containsString("New Name!"));
  }
}
