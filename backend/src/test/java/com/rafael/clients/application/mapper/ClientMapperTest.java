package com.rafael.clients.application.mapper;

import com.rafael.clients.application.dto.AddressDTO;
import com.rafael.clients.application.dto.ClientRequestDTO;
import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Address;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.model.Phone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientMapperTest {

    private PhoneMapper phoneMapper;
    private AddressMapper addressMapper;
    private ClientMapper clientMapper;

    @BeforeEach
    void setUp() {
        phoneMapper = mock(PhoneMapper.class);
        addressMapper = mock(AddressMapper.class);
        clientMapper = new ClientMapper(phoneMapper, addressMapper);
    }

    @Test
    void fromEntity_shouldConvertClientToDTO() {
        UUID id = UUID.randomUUID();
        List<Phone> phones = List.of(new Phone("999999999"));
        List<Address> addresses = List.of(new Address("São Paulo", "SP", "01000-000", "Av. Paulista", "Apto 101", "123", null));
        Client client = new Client("João", "12345678900");
        client.setId(id);
        phones.forEach(client::addPhone);
        addresses.forEach(client::addAddress);

        List<PhoneDTO> phoneDTOs = List.of(new PhoneDTO("11999999999"));
        List<AddressDTO> addressDTOs = List.of(new AddressDTO("São Paulo", "SP", "01000-000", "Apto 101", "Av. Paulista", null, "123"));

        when(phoneMapper.fromEntity(phones)).thenReturn(phoneDTOs);
        when(addressMapper.fromEntity(addresses)).thenReturn(addressDTOs);

        ClientResponseDTO dto = clientMapper.fromEntity(client);

        assertEquals(id, dto.id());
        assertEquals("João", dto.name());
        assertEquals("12345678900", dto.cpf());
        assertEquals(phoneDTOs, dto.phones());
        assertEquals(addressDTOs, dto.addresses());
    }

    @Test
    void fromEntity_shouldConvertClientListToDTOList() {
        List<Client> clients = new ArrayList<>();
        Client client = new Client("Maria", "09876543210");
        client.setId(UUID.randomUUID());

        List<Phone> phones = List.of(new Phone("21888888888"));
        List<Address> addresses = List.of(new Address("Rio de Janeiro", "RJ", "20000-000", "Rua Central", "Casa", "456", null));

        client.setPhoneNumbers(phones);
        client.setAddresses(addresses);

        clients.add(client);

        List<PhoneDTO> phoneDTOs = List.of(new PhoneDTO("888888888"));
        List<AddressDTO> addressDTOs = List.of(new AddressDTO("Rio de Janeiro", "RJ", "20000-000", "Casa", "Rua Central", null, "456"));

        when(phoneMapper.fromEntity(phones)).thenReturn(phoneDTOs);
        when(addressMapper.fromEntity(addresses)).thenReturn(addressDTOs);

        List<ClientResponseDTO> dtos = clientMapper.fromEntity(clients);

        assertEquals(1, dtos.size());
        assertEquals("Maria", dtos.get(0).name());
    }

    @Test
    void toEntity_shouldConvertDTOToClient() {
        List<PhoneDTO> phoneDTOs = List.of(new PhoneDTO("777777777"));
        List<AddressDTO> addressDTOs = List.of(new AddressDTO("Belo Horizonte", "MG", "30000-000", "Sala 2", "Av. Afonso Pena", null, "789"));

        List<Phone> phones = List.of(new Phone("777777777"));
        List<Address> addresses = List.of(new Address("Belo Horizonte", "MG", "30000-000", "Av. Afonso Pena", "Sala 2", "789", null));

        ClientRequestDTO dto = new ClientRequestDTO("Carlos", "11122233344", phoneDTOs, addressDTOs);

        when(phoneMapper.toEntity(phoneDTOs)).thenReturn(phones);
        when(addressMapper.toEntity(addressDTOs)).thenReturn(addresses);

        Client client = clientMapper.toEntity(dto);

        assertEquals("Carlos", client.getName());
        assertEquals("11122233344", client.getCpf());
        assertEquals(phones, client.getPhoneNumbers());
        assertEquals(addresses, client.getAddresses());
    }

    @Test
    void toEntity_withId_shouldSetIdAndConvertDTOToClient() {
        UUID id = UUID.randomUUID();
        List<PhoneDTO> phoneDTOs = List.of(new PhoneDTO("666666666"));
        List<AddressDTO> addressDTOs = List.of(new AddressDTO("Porto Alegre", "RS", "90000-000", "Bloco C", "Av. Ipiranga", null, "321"));

        List<Phone> phones = List.of(new Phone("666666666"));
        List<Address> addresses = List.of(new Address("Porto Alegre", "RS", "90000-000", "Av. Ipiranga", "Bloco C", "321", null));

        ClientRequestDTO dto = new ClientRequestDTO("Ana", "55566677788", phoneDTOs, addressDTOs);

        when(phoneMapper.toEntity(phoneDTOs)).thenReturn(phones);
        when(addressMapper.toEntity(addressDTOs)).thenReturn(addresses);

        Client client = clientMapper.toEntity(id, dto);

        assertEquals(id, client.getId());
        assertEquals("Ana", client.getName());
        assertEquals("55566677788", client.getCpf());
        assertEquals(phones, client.getPhoneNumbers());
        assertEquals(addresses, client.getAddresses());
    }

    @Test
    void toEntity_shouldHandleNullPhonesAndAddresses() {
        ClientRequestDTO dto = new ClientRequestDTO("Lara", "22233344455", null, null);

        Client client = clientMapper.toEntity(dto);

        assertEquals("Lara", client.getName());
        assertEquals("22233344455", client.getCpf());
        assertTrue(client.getPhoneNumbers().isEmpty());
        assertTrue(client.getAddresses().isEmpty());
    }
}