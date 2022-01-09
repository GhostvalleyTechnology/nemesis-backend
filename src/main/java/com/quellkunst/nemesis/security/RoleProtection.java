package com.quellkunst.nemesis.security;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RoleProtection implements RoleProtected {

    private final IllegalCallerException INSUFFICIENT_RIGHTS = new IllegalCallerException(
            "Insufficient Rights");

    @Inject
    Context context;

    @Override
    public <T> T asAdmin(AdminCommand<T> action) {
        if (context.getUser().isAdmin()) {
            return action.run();
        }
        throw INSUFFICIENT_RIGHTS;
    }
}
