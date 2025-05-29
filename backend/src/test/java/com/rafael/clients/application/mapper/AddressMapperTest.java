package com.rafael.clients.application.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafael.clients.application.dto.AddressDTO;
import com.rafael.clients.domain.model.Address;

class AddressMapperTest {

    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        addressMapper = new AddressMapper();
    }

    @Test
    void fromEntity_shouldConvertAddressSetToDTOSet() {
        Address address = new Address("São Paulo", "SP", "01000-000", "Av. Paulista", "123", "Apto 101");
        Set<Address> addresses = Set.of(address);

        Set<AddressDTO> dtos = addressMapper.fromEntity(addresses);

        assertEquals(1, dtos.size());

        AddressDTO dto = dtos.iterator().next();
        assertEquals("São Paulo", dto.city());
        assertEquals("SP", dto.state());
        assertEquals("01000-000", dto.zipCode());
        assertEquals("Av. Paulista", dto.publicPlace());
        assertEquals("123", dto.street());
        assertEquals("Apto 101", dto.complement());
    }

    @Test
    void fromEntity_shouldReturnEmptySet_whenInputIsEmpty() {
        Set<AddressDTO> result = addressMapper.fromEntity(Set.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void toEntity_shouldConvertDTOSetToAddressSet() {
        AddressDTO dto = new AddressDTO("São Paulo", "SP", "01000-000", "Av. Paulista", "123", "Apto 101");
        Set<AddressDTO> dtos = Set.of(dto);

        Set<Address> addresses = addressMapper.toEntity(dtos);

        assertEquals(1, addresses.size());

        Address address = addresses.iterator().next();
        assertEquals("São Paulo", address.getCity());
        assertEquals("SP", address.getState());
        assertEquals("01000-000", address.getZipCode());
        assertEquals("Av. Paulista", address.getPublicPlace());
        assertEquals("123", address.getStreet());
        assertEquals("Apto 101", address.getComplement());
    }

    @Test
    void toEntity_shouldReturnEmptySet_whenInputIsEmpty() {
        Set<Address> result = addressMapper.toEntity(Set.of());
        assertTrue(result.isEmpty());
    }
}
