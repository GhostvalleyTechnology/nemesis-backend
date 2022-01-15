package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.security.Context;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.security.TestSecurity;
import io.quarkus.test.security.oidc.Claim;
import io.quarkus.test.security.oidc.OidcSecurity;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@QuarkusTest
public class UserServiceTest {
    @InjectMock
    Context context;

    @BeforeEach
    public void setup() {
        Mockito.when(context.getEmail()).thenReturn("admin@quellkunst.com");
    }

    @Test
    public void testMe() {
        given().when().get("/api/me").then().body(containsString("wat"));
    }
}
