package com.rafael.clients.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.repository.ClientRepository;
import com.rafael.clients.domain.repository.PhoneRepository;

@Service
public class ClientDomainService {

    private final PhoneRepository phoneRepository;
    private final ClientRepository clientRepository;

    public ClientDomainService(ClientRepository clientRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    public void validateCpf(String cpf) {
        if (cpf == null || !isCpfValid(cpf)) {
            throw new ClientException("CPF invalid: " + cpf);
        }
    }

    public void validateDuplicity(Client client) {
        if (clientRepository.existsByCpf(client.getCpf())) {
            throw new ClientException("CPF already registered: " + client.getCpf());
        }
        if (clientRepository.existsByName(client.getName())) {
            throw new ClientException("Name already registered: " + client.getName());
        }
        client.getPhoneNumber().forEach(phone -> {
            if (phoneRepository.existsByPhoneNumber(phone.getPhoneNumber())) {
                throw new ClientException("Phone already registered: " + phone.getPhoneNumber());
            }
        });
    }

    public Client findById(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientException("Client not found with id: " + id));
    }

    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new ClientException("No client was found.");
        }
        return clients;
    }

    private boolean isCpfValid(String cpf) {
        return cpf.matches("\\d{11}");
    }
}
