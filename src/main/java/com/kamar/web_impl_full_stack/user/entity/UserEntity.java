package com.kamar.web_impl_full_stack.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * the user entity.
 * @author kamar baraka.*/

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID userId;
    @Column(unique = true)
    private String email;
    private String mobileNumber;
    private byte[] storedHash;
    private byte[] storedSalt;
}
