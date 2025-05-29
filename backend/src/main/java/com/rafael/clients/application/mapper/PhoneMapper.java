package com.rafael.clients.application.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Phone;

@Component
public class PhoneMapper {

    /**
     * Converts a set of {@link Phone} to a set of
     * {@link PhoneDTO} object.
     *
     * @param phones the set of {@link Phone} to be converted.
     * @return a set of {@link PhoneDTO} representing the given dto.
     */
    public Set<PhoneDTO> fromEntity(final Set<Phone> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream()
                .map(phone -> new PhoneDTO(phone.getPhoneNumber()))
                .collect(Collectors.toSet());
    }

    /**
     * Converts a set of {@link PhoneDTO} to a set of
     * {@link Phone} object.
     *
     * @param phoneDTOs the set of {@link PhoneDTO} to be converted.
     * @return a set of {@link Phone} representing the given dto.
     */
    public Set<Phone> toEntity(final Set<PhoneDTO> phoneDTOs) {
        if (phoneDTOs == null) {
            return null;
        }
        return phoneDTOs.stream()
                .map(dto -> new Phone(dto.number()))
                .collect(Collectors.toSet());
    }
}
