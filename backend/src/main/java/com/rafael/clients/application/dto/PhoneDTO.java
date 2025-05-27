package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneDTO(
                @NotBlank @Pattern(regexp = "\\+?\\d{10,15}") String number) {
}
