package com.quellkunst.nemesis;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import com.quellkunst.nemesis.security.AppContext;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class ExampleResourceTest {
  @InjectMock AppContext context;

  @BeforeEach
  public void setup() {
    Mockito.when(context.getEmail()).thenReturn("admin@quellkunst.com");
  }

  @Test
  @TestSecurity(authorizationEnabled = false)
  public void testHelloEndpoint() {
    given().when().get("/api/hello").then().body(containsString("admin@quellkunst.com"));
  }
}
