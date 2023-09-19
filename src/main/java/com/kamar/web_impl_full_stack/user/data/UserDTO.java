package com.kamar.web_impl_full_stack.user.data;

import java.util.UUID;

/**
 * the user DTO.
 * @author kamar baraka.*/

public interface UserDTO {

    UUID getUserId();
    String getEmail();
    String getMobileNumber();
    String getPassword();

}
