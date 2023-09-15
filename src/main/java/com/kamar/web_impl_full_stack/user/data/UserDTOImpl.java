package com.kamar.web_impl_full_stack.user.data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * the user DTO.
 * @author kamar baraka.*/

@Getter
@Setter
public class UserDTOImpl implements UserDTO {

    private UUID userId;
    private String email;
    private String mobileNumber;
    private String password;
}
