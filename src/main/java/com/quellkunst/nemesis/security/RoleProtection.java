package com.quellkunst.nemesis.security;

import io.quarkus.logging.Log;

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
        if (context.getCurrentEmployee().admin) {
            return action.run();
        }
        throw INSUFFICIENT_RIGHTS;
    }

    @Override
    public void asAdmin(Runnable action) {
        asAdmin(() -> {
            action.run();
            return null;
        });
    }

    @Override
    public <T> T asSuperAdmin(AdminCommand<T> action) {
        if ("admin@quellkunst.com".equals(context.getEmail())) {
            Log.info("The SuperAdmin has been called!");
            return action.run();
        }
        throw INSUFFICIENT_RIGHTS;
    }

    @Override
    public void asSuperAdmin(Runnable action) {
        asSuperAdmin(() -> {
            action.run();
            return null;
        });
    }

}
