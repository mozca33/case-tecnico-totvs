package com.rafael.clients.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
        Address address = new Address("São Paulo", "SP", "Av. Paulista", "Apto 101","01000-000", "123", null);
        List<Address> addresses = List.of(address);

        List<AddressDTO> dtos = addressMapper.fromEntity(addresses);

        assertEquals(1, dtos.size());

        AddressDTO dto = dtos.iterator().next();
        assertEquals("São Paulo", dto.city());
        assertEquals("SP", dto.state());
        assertEquals("Av. Paulista", dto.street()); 
        assertEquals("01000-000", dto.zipCode());
        assertEquals("Apto 101", dto.complement()); 
        assertEquals("123", dto.number());
        assertNull(dto.publicPlace()); 
    }

    @Test
    void fromEntity_shouldReturnEmptySet_whenInputIsEmpty() {
        List<AddressDTO> result = addressMapper.fromEntity(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void toEntity_shouldConvertDTOSetToAddressSet() {
        AddressDTO dto = new AddressDTO("São Paulo", "SP", "Av. Paulista", "Apto 101","01000-000", null, "123");
        List<AddressDTO> dtos = List.of(dto);

        List<Address> addresses = addressMapper.toEntity(dtos);

        assertEquals(1, addresses.size());

        Address address = addresses.iterator().next();
        assertEquals("São Paulo", address.getCity());
        assertEquals("SP", address.getState());
        assertEquals("Apto 101", address.getComplement()); 
        assertEquals("Av. Paulista", address.getStreet());
        assertEquals("01000-000", address.getZipCode());
        assertNull(address.getNumber()); 
        assertEquals("123", address.getPublicPlace()); 
    }

    @Test
    void toEntity_shouldReturnEmptySet_whenInputIsEmpty() {
        List<Address> result = addressMapper.toEntity(List.of());
        assertTrue(result.isEmpty());
    }
}