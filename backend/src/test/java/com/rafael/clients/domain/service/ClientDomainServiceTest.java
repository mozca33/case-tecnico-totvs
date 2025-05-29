package com.rafael.clients.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Address;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.ClientRepository;
import com.rafael.clients.domain.validator.AddressValidator;
import com.rafael.clients.domain.validator.PhoneValidator;

class ClientDomainServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private PhoneValidator phoneValidator;
    @Mock
    private AddressValidator addressValidator;

    @InjectMocks
    private ClientDomainService clientDomainService;

    private Client client;
    private UUID id;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        id = UUID.randomUUID();
        client = new Client();
        client.setId(id);
        client.setCpf("12345678909"); // CPF válido fictício
        client.setName("John Doe");
        client.setPhoneNumbers(Set.of(new Phone("987654321")));
        client.setAddresses(Set
                .of(new Address("city", "state", "12345-678", "public place", "street", "complement of the address")));
    }

    @Test
    void findAll_shouldReturnClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> result = clientDomainService.findAll();

        assertEquals(1, result.size());
        verify(clientRepository).findAll();
    }

    @Test
    void findAll_shouldThrowException_whenNoClientFound() {
        when(clientRepository.findAll()).thenReturn(Collections.emptyList());

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.findAll());
        assertEquals(MessageConstants.NO_CLIENT_WAS_FOUND, ex.getMessage());
    }

    @Test
    void createClient_shouldSaveValidClient() {
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);
        when(clientRepository.findByName(client.getName())).thenReturn(Optional.empty());

        Client created = clientDomainService.createClient(client);

        verify(clientRepository).save(client);
        assertEquals(client, created);
    }

    @Test
    void createClient_shouldThrowException_whenCpfAlreadyExists() {
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(true);

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.createClient(client));
        assertEquals(MessageConstants.CLIENT_ALREADY_EXISTS, ex.getMessage());
    }

    @Test
    void updateClient_shouldUpdateExistingClient() {
        Client existingClient = new Client();
        existingClient.setId(id);
        existingClient.setName("Old Name");

        when(clientRepository.findById(id)).thenReturn(Optional.of(existingClient));
        when(clientRepository.findByName(client.getName())).thenReturn(Optional.empty());

        Client updated = clientDomainService.updateClient(id, client);

        verify(clientRepository).save(existingClient);
        assertEquals(client, updated);
    }

    @Test
    void updateClient_shouldThrowException_whenClientNotFound() {
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.updateClient(id, client));
        assertTrue(ex.getMessage().contains(MessageConstants.CLIENT_NOT_FOUND_WITH_ID));
    }

    @Test
    void findById_shouldReturnClient() {
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        Client result = clientDomainService.findById(id);

        assertEquals(client, result);
    }

    @Test
    void findById_shouldThrowException_whenNotFound() {
        when(clientRepository.findById(id)).thenReturn(Optional.empty());

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.findById(id));
        assertTrue(ex.getMessage().contains(MessageConstants.CLIENT_NOT_FOUND_WITH_ID));
    }

    @Test
    void deleteClient_shouldDelete_whenExists() {
        when(clientRepository.existsById(id)).thenReturn(true);

        clientDomainService.deleteClient(id);

        verify(clientRepository).deleteById(id);
    }

    @Test
    void deleteClient_shouldThrowException_whenNotFound() {
        when(clientRepository.existsById(id)).thenReturn(false);

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.deleteClient(id));
        assertEquals(MessageConstants.CLIENT_NOT_FOUND, ex.getMessage());
    }

    @Test
    void validateCpf_shouldThrowException_whenInvalidCpf() {
        client.setCpf("11111111111"); // CPF inválido

        assertThrows(ClientException.class, () -> clientDomainService.createClient(client));
    }

    @Test
    void validateName_shouldThrow_whenClientWithSameNameExists() {
        Client existingClient = new Client();
        existingClient.setId(UUID.randomUUID());
        when(clientRepository.existsByCpf(client.getCpf())).thenReturn(false);
        when(clientRepository.findByName(client.getName())).thenReturn(Optional.of(existingClient));

        ClientException ex = assertThrows(ClientException.class, () -> clientDomainService.createClient(client));
        assertEquals(MessageConstants.CLIENT_ALREADY_EXISTS_WITH_NAME, ex.getMessage());
    }
}
