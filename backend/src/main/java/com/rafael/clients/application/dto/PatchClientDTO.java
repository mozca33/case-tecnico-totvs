package com.rafael.clients.application.dto;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;

public record PatchClientDTO(
    @Schema(
        description = "Nome completo do cliente. Opcional para atualização.",
        example = "Rafael Silva"
    )
    Optional<String> name,

    @Schema(
        description = "CPF do cliente, somente números. Opcional para atualização.",
        example = "12345678901"
    )
    Optional<String> cpf,

    @Schema(
        description = "Lista de telefones do cliente. Opcional para atualização."
    )
    Optional<List<PhoneDTO>> phones,

    @Schema(
        description = "Lista de endereços do cliente. Opcional para atualização."
    )
    Optional<List<AddressDTO>> addresses
) {}