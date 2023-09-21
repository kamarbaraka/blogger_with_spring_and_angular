package com.kamar.web_impl_full_stack.jwt.models;

import lombok.Getter;
import lombok.Setter;

/**
 * the authentication request.
 * @author kamar baraka.*/

@Getter
@Setter
public class AuthenticationRequest {

    private String email;
    private String password;
}
