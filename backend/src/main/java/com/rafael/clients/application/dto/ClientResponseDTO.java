package com.rafael.clients.application.dto;

import java.util.Set;
import java.util.UUID;

public record ClientResponseDTO(
        UUID id,
        String name,
        String cpf,
        Set<PhoneDTO> phones,
        Set<AddressDTO> addresses) {
}
