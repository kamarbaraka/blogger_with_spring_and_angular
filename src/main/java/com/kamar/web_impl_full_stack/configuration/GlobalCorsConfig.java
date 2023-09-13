package com.kamar.web_impl_full_stack.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * the global CORS config for the application.
 * @author kamar baraka.*/

@Configuration
public class GlobalCorsConfig {


    /*The CORS Filter Bean*/
    @Bean
    public CorsFilter corsFilter(){

        /*construct a Cors configuration object*/
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        /*configure the CORS*/
        /*set allow credentials*/
        corsConfiguration.setAllowCredentials(true);
        /*set allowed methods*/
        corsConfiguration.setAllowedMethods(List.of("POST", "GET", "OPTIONS", "DELETE", "PUT"));
        /*set allowed headers*/
        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Content-Type", "Accept", "Authorization", "Origin", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Methods", "Access-Control-Request-Headers", "Access-Control-Allow-Origin"
        ));
        /*set exposed headers*/
        corsConfiguration.setExposedHeaders(List.of(
                "Content-Type", "Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Methods",
                "Access-Control-Allow-Credentials", "Authorization"
        ));

        /*construct an url-based CORS configuration source to register the CORS configuration*/
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        /*register the CORS configuration */
        configurationSource.registerCorsConfiguration("/**", corsConfiguration);
        /*construct and return the Cors filter*/
        return new CorsFilter(configurationSource);
    }
}
