package com.kamar.web_impl_full_stack.jwt.util;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

/**
 * the jwt utils service.
 * @author kamar baraka.*/

public interface JwtUtils {

    String generateToken(UserDetails userDetails);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Date extractExpiration(String token);
    boolean isTokenExpired(String token);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
}
