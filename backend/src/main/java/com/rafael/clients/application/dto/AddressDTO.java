package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import com.rafael.clients.common.MessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddressDTO(
    @Schema(
        description = "Cidade do endereço",
        example = "São Paulo"
    )
    @NotBlank
    String city,

    @Schema(
        description = "Estado do endereço",
        example = "SP"
    )
    @NotBlank
    String state,

    @Schema(
        description = "Rua",
        example = "Av. Paulista"
    )
    @NotBlank
    String street,

    @Schema(
        description = "Complemento",
        example = "Apto 101"
    )
    @NotBlank
    String complement,

    @Schema(
        description = "CEP no formato 00000-000",
        example = "01311-000",
        pattern = "\\d{5}-\\d{3}"
    )
    @NotBlank(message = MessageConstants.ZIPCODE_MUST_NOT_BE_BLANK)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = MessageConstants.INVALID_ZIPCODE_FORMAT)
    String zipCode,

    @Schema(
        description = "Número do endereço",
        example = "1578"
    )
    String number,

    @Schema(
        description = "Logradouro público",
        example = "Avenida"
    )
    String publicPlace
) {}