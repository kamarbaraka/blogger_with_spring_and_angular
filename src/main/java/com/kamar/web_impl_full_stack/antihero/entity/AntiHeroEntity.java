package com.kamar.web_impl_full_stack.antihero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * the anti-hero entity.
 * @author kamar baraka.*/

@Entity
@Table(name = "anti_hero")
@Getter
@Setter
public class AntiHeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotNull(message = "first name cannot be null")
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;
    @Column(nullable = false, updatable = false)
    private final String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
}
