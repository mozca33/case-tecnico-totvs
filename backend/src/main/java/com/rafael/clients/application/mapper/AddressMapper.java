package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.AddressDTO;
import com.rafael.clients.domain.model.Address;

@Component
public class AddressMapper {

    /**
     * @param addresses
     * @return
     */
    public static Set<AddressDTO> fromEntity(final Set<Address> addresses) {
        return addresses.stream()
                .map(address -> new AddressDTO(address.getPublicPlace(), address.getCity(), address.getState(),
                        address.getCep(), address.getComplement()))
                .collect(Collectors.toSet());
    }

    public static Set<Address> toEntity(final List<AddressDTO> addressDTOs) {
        return addressDTOs.stream()
                .map(dto -> new Address(dto.publicPlace(), dto.city(), dto.state(), dto.cep(), dto.complement()))
                .collect(Collectors.toSet());
    }

}
