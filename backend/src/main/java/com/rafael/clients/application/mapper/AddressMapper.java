package com.rafael.clients.application.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.AddressDTO;
import com.rafael.clients.domain.model.Address;

@Component
public class AddressMapper {

    /**
     * Converts a Set of {@link Address} entities to a set of
     * {@link AddressDTO} objects.
     *
     * @param addresses the set of {@link Address} entities to be converted.
     * @return a set of {@link AddressDTO} representing the given entities.
     */
    public Set<AddressDTO> fromEntity(final Set<Address> addresses) {
        return addresses.stream()
                .map(address -> new AddressDTO(address.getCity(), address.getState(), address.getZipCode(),
                        address.getPublicPlace(), address.getStreet(), address.getComplement()))
                .collect(Collectors.toSet());
    }

    /**
     * Converts a Set of {@link AddressDTO} to a set of
     * {@link Address} objects.
     *
     * @param addressDTOs the set of {@link AddressDTO} to be converted.
     * @return a set of {@link Address} representing the given dto.
     */
    public Set<Address> toEntity(final Set<AddressDTO> addressDTOs) {
        return addressDTOs.stream()
                .map(dto -> new Address(dto.city(), dto.state(), dto.zipCode(), dto.publicPlace(), dto.street(),
                        dto.complement()))
                .collect(Collectors.toSet());
    }
}
