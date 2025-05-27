package com.rafael.clients.application.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record ClientRequestDTO(
        @NotBlank @Size(min = 10) String name,

        @NotBlank String cpf,

        @NotEmpty List<PhoneDTO> phones,

        @NotEmpty List<AddressDTO> addresses) {

}
