package com.quellkunst.nemesis.security;

import com.quellkunst.nemesis.model.Employee;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

import static com.quellkunst.nemesis.security.ExceptionSupplier.theException;

@ApplicationScoped
public class AppContext implements Context {

    @Inject
    JsonWebToken idToken;

    private String getClaim(Claims claim) {
        Optional<String> jwtEmail = idToken.claim(claim);
        return jwtEmail.orElseThrow(claimException(claim));
    }

    private boolean isAdmin() {
        return Employee.getByEmail(getClaim(Claims.email)).isAdminRights();
    }

    private ExceptionSupplier<IllegalCallerException> claimException(Claims claim) {
        var msg = claim.getDescription() + "not configured!";
        return theException(new IllegalCallerException(msg));
    }

    @Override
    public User getUser() {
        return User.builder()
                .name(getClaim(Claims.full_name))
                .email(getClaim(Claims.email))
                .admin(isAdmin()).build();
    }


}
