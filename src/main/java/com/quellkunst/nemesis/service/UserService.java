package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.security.Context;
import com.quellkunst.nemesis.security.User;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/me")
public class UserService {

    @Inject
    Context context;

    @GET
    public User get() {
        return context.getUser();
    }
}
