package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.AddressDTO;
import com.rafael.clients.domain.model.Address;

@Component
public class AddressMapper {

    /**
     * Converts a List of {@link Address} entities to a list of
     * {@link AddressDTO} objects.
     *
     * @param addresses the list of {@link Address} entities to be converted.
     * @return a list of {@link AddressDTO} representing the given entities.
     */
    public List<AddressDTO> fromEntity(final List<Address> addresses) {
        return addresses.stream()
                .map(address -> new AddressDTO(
                        address.getCity(), 
                        address.getState(), 
                        address.getStreet(), 
                        address.getComplement(), 
                        address.getZipCode(),
                        address.getNumber(),
                        address.getPublicPlace()
                ))
                .collect(Collectors.toList()); // Mudança aqui: toList() em vez de toSet()
    }

    /**
     * Converts a List of {@link AddressDTO} to a list of
     * {@link Address} objects.
     *
     * @param addressDTOs the list of {@link AddressDTO} to be converted.
     * @return a list of {@link Address} representing the given dto.
     */
    public List<Address> toEntity(final List<AddressDTO> addressDTOs) {
        return addressDTOs.stream()
                .map(dto -> new Address(dto.city(), dto.state(), dto.street(), dto.complement(),
                        dto.zipCode(), dto.number(), dto.publicPlace())
                )
                .collect(Collectors.toList()); // Mudança aqui: toList() em vez de toSet()
    }
}