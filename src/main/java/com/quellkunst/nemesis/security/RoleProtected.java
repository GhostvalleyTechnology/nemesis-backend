package com.quellkunst.nemesis.security;

/**
 * Currently, as we do not support role authentication through auth0, this interface provides a simple check if the current use has the needed authority.
 */
public interface RoleProtected {

    /**
     * Checks if the current user has admin rights. If so, the action is executed. If not, an exception is thrown
     *
     * @param action to execute
     */
    <T> T asAdmin(AdminCommand<T> action);
}