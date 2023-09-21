package com.kamar.web_impl_full_stack.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * the application security configuration
 * @author kamar baraka.*/

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        /*configure the http security*/
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll().anyRequest().authenticated()).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        /*build and return the configured http security*/
        return httpSecurity.build();
    }
}
