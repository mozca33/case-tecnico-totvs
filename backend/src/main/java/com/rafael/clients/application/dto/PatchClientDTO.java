package com.rafael.clients.application.dto;

import java.util.Optional;
import java.util.Set;

public record PatchClientDTO(
                Optional<String> name,
                Optional<String> cpf,
                Optional<Set<PhoneDTO>> phones,
                Optional<Set<AddressDTO>> addresses) {
}
