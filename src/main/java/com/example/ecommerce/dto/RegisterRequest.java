package com.example.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest( @NotNull @NotBlank @NotEmpty String name, @NotNull @NotBlank @NotEmpty @Email String email,
                               @NotNull @NotBlank @NotEmpty String password,
                              @JsonProperty("role_id") Long roleId,
                              StoreRequestDto store) {
}
