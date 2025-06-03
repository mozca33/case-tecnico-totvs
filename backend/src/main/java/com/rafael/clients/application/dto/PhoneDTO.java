package com.rafael.clients.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import com.rafael.clients.common.MessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;

public record PhoneDTO(
    @Schema(
        description = "Telefone no formato nacional ou com DDI (+55). Exemplo: +5511999999999 ou 11999999999",
        example = "+5511999999999", 
        pattern = "^(?:\\+55)?[1-9]{2}9?\\d{8,9}$"
    )
    @NotBlank(message = MessageConstants.PHONE_MUST_NOT_BE_BLANK)
    @Pattern(regexp = "^(?:\\+55)?[1-9]{2}9?\\d{8,9}$", message =  MessageConstants.PHONE_NUMBER_CONSTRAINT)
    String number
) {}