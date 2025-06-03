package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.stream.Collectors;
import com.rafael.clients.domain.service.NormalizerService;
import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Phone;

@Component
public class PhoneMapper {

    private final NormalizerService normalizerService;

    PhoneMapper(NormalizerService normalizerService) {
        this.normalizerService = normalizerService;
    }

    /**
     * Converts a list of {@link Phone} to a list of
     * {@link PhoneDTO} object.
     *
     * @param phones the list of {@link Phone} to be converted.
     * @return a list of {@link PhoneDTO} representing the given dto.
     */
    public List<PhoneDTO> fromEntity(final List<Phone> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream()
                .map(phone -> new PhoneDTO(phone.getPhoneNumber()))
                .collect(Collectors.toList()); // Mudança: toList() em vez de toSet()
    }

    /**
     * Converts a list of {@link PhoneDTO} to a list of
     * {@link Phone} object.
     *
     * @param phoneDTOs the list of {@link PhoneDTO} to be converted.
     * @return a list of {@link Phone} representing the given dto.
     */
    public List<Phone> toEntity(final List<PhoneDTO> phoneDTOs) {
        if (phoneDTOs == null) {
            return null;
        }
        return phoneDTOs.stream()
                .map(dto -> new Phone(dto.number()))
                .map(normalizerService::normalize)
                .collect(Collectors.toList()); 
    }
}