package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneDTO(
        @NotBlank @Pattern(regexp = "^(?:\\+55)?[1-9]{2}9?\\d{8}$") String number) {
}
