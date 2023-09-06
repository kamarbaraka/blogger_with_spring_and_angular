package com.kamar.web_impl_full_stack.antihero.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * the anti-hero dto (data transfer object)
 * @author kamar baraka.*/


@Getter
@Setter
public class AntiHeroDTO {

    private UUID id;
    @NotNull(message = "the first name is required")
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;
}
