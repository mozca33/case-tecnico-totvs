package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Phone;

@Component
public class PhoneMapper {

    public static Set<PhoneDTO> fromEntity(final Set<Phone> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream()
                .map(phone -> new PhoneDTO(phone.getPhoneNumber()))
                .collect(Collectors.toSet());
    }

    public static Set<Phone> toEntity(final List<PhoneDTO> phoneDTOs) {
        if (phoneDTOs == null) {
            return null;
        }
        return phoneDTOs.stream()
                .map(dto -> new Phone(dto.number()))
                .collect(Collectors.toSet());
    }

}
