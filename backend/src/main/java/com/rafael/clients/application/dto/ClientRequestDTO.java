package com.rafael.clients.application.dto;

import java.util.List;

import com.rafael.clients.common.MessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestDTO(
    @Schema(
        description = "Nome completo do cliente",
        example = "Rafael Silva",
        minLength = 10,
        maxLength = 255,
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = MessageConstants.NAME_CONSTRAINT) @Size(min = 10, max = 255, message = MessageConstants.NAME_CONSTRAINT) String name,


    @Schema(
        description = "CPF do cliente, somente números",
        example = "12345678901",
        pattern = "\\d{11}",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = MessageConstants.CPF_MUST_NOT_BE_BLANK) @Pattern(regexp = "\\d{11}", message = MessageConstants.CPF_CONSTRAINT) String cpf,

    @Schema(
        description = "Lista de telefones do cliente",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = MessageConstants.PHONE_MUST_NOT_BE_EMPTY) @Valid List<PhoneDTO> phones,

    @Schema(
        description = "Lista de endereços do cliente",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotEmpty(message = MessageConstants.ADDRESS_MUST_NOT_BE_EMPTY) @Valid List<AddressDTO> addresses
) {}
