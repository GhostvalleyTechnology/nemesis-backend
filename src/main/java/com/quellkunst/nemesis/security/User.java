package com.quellkunst.nemesis.security;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private String name;
    private String email;
    private boolean admin;
}
