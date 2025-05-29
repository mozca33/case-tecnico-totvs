package com.rafael.clients.application.dto;

import java.util.Set;

import com.rafael.clients.common.MessageConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestDTO(
                @NotBlank @Size(min = 10, max = 255, message = MessageConstants.NAME_CONSTRAINTS) String name,

                @NotBlank @Pattern(regexp = "\\d{11}") String cpf,

                @NotEmpty Set<PhoneDTO> phones,

                @NotEmpty Set<AddressDTO> addresses) {

}
