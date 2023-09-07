package com.kamar.web_impl_full_stack.antihero.exception;

/**
 * exception returned when resource is not found.
 * @author kamar baraka.*/

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message, Throwable cause){

        super(message, cause);
    }
    public NotFoundException(String message){

        super(message);
    }
    public NotFoundException(){

        super();
    }
}
