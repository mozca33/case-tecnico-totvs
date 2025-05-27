package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
                @NotBlank String publicPlace,
                @NotBlank String city,
                @NotBlank String state,
                @NotBlank String cep,
                @NotBlank String complement) {

}
