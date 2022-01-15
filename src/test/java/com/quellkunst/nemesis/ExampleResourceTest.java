package com.quellkunst.nemesis;

import com.quellkunst.nemesis.security.Context;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(ExampleResource.class)
public class ExampleResourceTest {
    @InjectMock
    Context context;

    @BeforeEach
    public void setup() {
        Mockito.when(context.getEmail()).thenReturn("admin@quellkunst.com");
    }

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/")
                .then()
                .statusCode(200).and()
                .body(containsString("admin@quellkunst.com"));
    }

}