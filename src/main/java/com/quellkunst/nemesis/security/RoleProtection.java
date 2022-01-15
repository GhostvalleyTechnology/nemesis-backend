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
        if ("admin@quellkunst.com".equals(context.getEmail()) ||
                context.getCurrentEmployee().admin) {
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

}
