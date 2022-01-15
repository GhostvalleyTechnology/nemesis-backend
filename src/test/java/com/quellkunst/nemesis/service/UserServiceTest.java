package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.security.Context;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class UserServiceTest {
  @InjectMock Context context;

  @BeforeAll
  public static void init() {}

  @BeforeEach
  public void setup() {
    Mockito.when(context.getEmail()).thenReturn("admin@quellkunst.com");
  }

  @Test
  public void testMe() {
    given().when().get("/api/me").then().body(containsString("wat"));
  }
}
