package com.kamar.web_impl_full_stack.jwt.services;

import com.kamar.web_impl_full_stack.jwt.models.UserPrincipal;
import com.kamar.web_impl_full_stack.user.entity.UserEntity;
import com.kamar.web_impl_full_stack.user.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * the application's user service implementation.
 * @author kamar baraka.*/

@Service
@AllArgsConstructor
@Log4j2
public class ApplicationUserDetailsServiceImpl implements ApplicationUserDetailsService {

    private UserService userService;



    @Override
    public UserEntity authenticate(String email, String password) throws NoSuchAlgorithmException {

        /*verify the email and password are not empty*/
        if (email.isEmpty() || password.isEmpty())
            throw new BadCredentialsException("unauthorized");

        /*check if the user exists*/
        UserEntity user = userService.findByEmail(email);
        if (user == null) throw new BadCredentialsException("unauthorized");

        /*verify the password hash*/
        boolean verified = this.verifyPasswordHash(password, user.getStoredHash(), user.getStoredSalt());

        /*return the user if verified*/
        if (!verified) throw new BadCredentialsException("unauthorized");
        return user;
    }

    @Override
    public boolean verifyPasswordHash(String password, byte[] storedHash, byte[] storedSalt) throws NoSuchAlgorithmException {

        /*verify that the password is not empty or blank*/
        if (password.isBlank() || password.isEmpty())
            throw new IllegalArgumentException("password is required");

        /*check whether the password hash and salt are 64 bytes*/
        if (storedHash.length != 64)
            throw new IllegalArgumentException("password hash should be 64 bytes");
        if (storedSalt.length != 128)
            throw new IllegalArgumentException("salt should be 64 bytes");

        /*get an instance of message digest to compute the password hash*/
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        /*update the digest with the stored salt*/
        digest.update(storedSalt);

        /*compute the password hash*/
        byte[] computedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        /*compare the computed hash against the stored hash*/
        return MessageDigest.isEqual(computedHash, storedHash);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*construct and return an implementation of User Details */
        UserEntity user = userService.findByEmail(username);
        return new UserPrincipal(user);
    }
}
