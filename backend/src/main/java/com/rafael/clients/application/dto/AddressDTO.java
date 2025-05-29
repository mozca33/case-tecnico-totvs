package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
                @NotBlank String city,
                @NotBlank String state,
                @NotBlank @Pattern(regexp = "\\d{5}-\\d{3}") String zipCode,
                @NotBlank String publicPlace,
                @NotBlank String street,
                String complement) {

}
