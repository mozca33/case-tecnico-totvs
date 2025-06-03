package com.rafael.clients.application.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClientResponseDTO(
    @Schema(
        description = "Identificador único do cliente",
        example = "b7e0ee7b-84c5-4e04-b7bd-87ebc02014f2"
    )
    UUID id,

    @Schema(
        description = "Nome completo do cliente",
        example = "Rafael Silva"
    )
    String name,

    @Schema(
        description = "CPF do cliente, somente números",
        example = "12345678901"
    )
    String cpf,

    @Schema(
        description = "Lista de telefones do cliente"
    )
    List<PhoneDTO> phones,

    @Schema(
        description = "Lista de endereços do cliente"
    )
    List<AddressDTO> addresses
) {}