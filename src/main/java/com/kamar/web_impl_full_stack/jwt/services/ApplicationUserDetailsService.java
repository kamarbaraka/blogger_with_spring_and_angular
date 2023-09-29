package com.kamar.web_impl_full_stack.jwt.services;

import com.kamar.web_impl_full_stack.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.NoSuchAlgorithmException;

/**
 * this application's user detail service.
 * @author kamar baraka.*/

public interface ApplicationUserDetailsService extends UserDetailsService {

    UserEntity authenticate(String email, String password) throws NoSuchAlgorithmException;
    boolean verifyPasswordHash(String password, byte[] storedHash, byte[] storedSalt) throws NoSuchAlgorithmException;
}
