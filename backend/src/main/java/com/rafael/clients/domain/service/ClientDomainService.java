package com.rafael.clients.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.repository.ClientRepository;
import com.rafael.clients.domain.validator.AddressValidator;
import com.rafael.clients.domain.validator.PhoneValidator;

import br.com.caelum.stella.validation.CPFValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ClientDomainService {

    private final ClientRepository clientRepository;
    private final PhoneValidator phoneValidator;
    private final AddressValidator addressValidator;

    @PersistenceContext
    private EntityManager entityManager;

    public ClientDomainService(ClientRepository clientRepository,
            PhoneValidator phoneValidator, AddressValidator addressValidator) {
        this.clientRepository = clientRepository;
        this.phoneValidator = phoneValidator;
        this.addressValidator = addressValidator;
    }

    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new ClientException(MessageConstants.NO_CLIENT_WAS_FOUND);
        }
        return clients;
    }

    @Transactional
    public Client createClient(Client client) {
        verifyExistsByCpf(client.getCpf());
        validateClient(client);

        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Client updateClient(UUID id, Client client) {
        Client existingClient = findById(id);
        client.setId(id);

        existingClient.mergeFrom(client);
        validateClient(existingClient);

        clientRepository.save(existingClient);
        return client;
    }

    public Client findById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException(MessageConstants.CLIENT_NOT_FOUND_WITH_ID + id));
    }

    @Transactional
    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientException(MessageConstants.CLIENT_NOT_FOUND);
        }
        clientRepository.deleteById(id);
    }

    /**
     * Verify if client already exists by CPF
     * 
     * @param cpf the cpf to be searched.
     * @throws ClientException
     */
    private void verifyExistsByCpf(String cpf) {
        if (clientRepository.existsByCpf(cpf)) {
            throw new ClientException(MessageConstants.CLIENT_ALREADY_EXISTS);
        }
    }

    /***
     * Validates the Cpf utilizing {@link CPFValidator} from caelum.stella.core
     * 
     * @param cpf the cpf to be validated.
     * @throws InvalidStateException.
     */
    private void validateCpf(Client client) {
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.assertValid(client.getCpf());
    }

    private void validateClient(Client client) {
        validateCpf(client);
        validateName(client);
        client.getPhoneNumbers().forEach(phoneValidator::validate);
        client.getAddresses().forEach(addressValidator::validate);
    }

    private void validateName(Client client) {
        Optional<Client> duplicate = clientRepository.findByNameAndIdNot(client.getName(), client.getId());
        if (duplicate.isPresent()) {
            throw new ClientException(MessageConstants.CLIENT_ALREADY_EXISTS_WITH_NAME);
        }
    }

}
