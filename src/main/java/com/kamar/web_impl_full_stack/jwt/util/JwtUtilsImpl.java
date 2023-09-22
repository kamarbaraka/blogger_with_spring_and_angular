package com.kamar.web_impl_full_stack.jwt.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * the jwt utilities service implementation.
 * @author kamar baraka.*/

@Service
public class JwtUtilsImpl implements JwtUtils {

    private String createToken(Map<String , Object> claims, String subject){

        /*create the jwt builder*/
        JwtBuilder builder = Jwts.builder();
        /*set the claims*/
        builder.setClaims(claims);
        /*set the subject*/
        builder.setSubject(subject);
        /*set the date of issue*/
        builder.setIssuedAt(new Date(System.currentTimeMillis()));
        /*set the expiration*/
        builder.setExpiration(new Date(System.currentTimeMillis() + 1000 + 60 + 60 +10));
        /*sign the jwt the secret key of the chosen algorithm*/
        builder.signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256));
        /*build and return the jwt*/
        return builder.compact();

    }
    @Override
    public String generateToken(UserDetails userDetails) {

        /*create the hash map containing the claims*/
        HashMap<String, Object> claims = new HashMap<>();

        /*generate and return the jwt token*/
        return this.createToken(claims, userDetails.getUsername());
    }

    private Claims extractAllClaims(String token){

        /*create a parser for the token*/
        JwtParserBuilder tokenParser = Jwts.parserBuilder();
        /*set the signing key for the parser*/
        tokenParser.setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256));
        /*parse the parser*/
        JwtParser jwtParser = tokenParser.build();
        /*extract the claims from the token using the parser*/
        Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        /*get and return the body of the claims*/
        return claimsJws.getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        /*extract the claims from the token*/
        final Claims claims = this.extractAllClaims(token);
        /*apply the provided claims resolver to the claims to resolve and return the resolved claims*/
        return claimsResolver.apply(claims);
    }

    @Override
    public Date extractExpiration(String token) {

        /*extract and return the expiration claim from the token*/
        return this.extractClaim(token, Claims::getExpiration);

    }

    @Override
    public boolean isTokenExpired(String token) {

        /*extract the expiration and compare*/
        Date expirationDate = this.extractExpiration(token);
        /*compare and return the boolean*/
        return expirationDate.before(new Date(System.currentTimeMillis()));

    }

    @Override
    public String extractUsername(String token) {

        /*extract the value of the username from the claims*/
        return this.extractClaim(token, Claims::getSubject);

    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {

        /*check whether the decoded username is equal to the detail's*/
        return this.extractUsername(token).equals(userDetails.getUsername()) && !this.isTokenExpired(token);

    }
}
