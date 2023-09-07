package com.kamar.web_impl_full_stack.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * the configuration of the app context.
 * @author kamar baraka.*/

@Configuration
public class AppContextConfig {

    /*register the model mapper object to map our DTOs*/
    @Bean
    public ModelMapper modelMapper(){
        /*return an instance of the model mapper*/
        return new ModelMapper();
    }

}
