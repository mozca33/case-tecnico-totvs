package com.rafael.clients.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.application.mapper.ClientMapper;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.repository.ClientRepository;
import com.rafael.clients.domain.service.ClientDomainService;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientDomainService clientDomainService;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientDomainService clientDomainService,
            ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientDomainService = clientDomainService;
        this.clientMapper = clientMapper;
    }

    @Transactional
    public ClientResponseDTO createClient(Client client) {

        clientDomainService.validateCpf(client.getCpf());
        clientDomainService.validateDuplicity(client);

        Client saved = clientRepository.save(client);

        return clientMapper.fromEntity(saved);
    }

    public ClientResponseDTO searchById(UUID id) {

        return clientMapper.fromEntity(clientDomainService.findById(id));
    }

    public Client getClientById(UUID id) {
        return clientDomainService.findById(id);
    }

    public List<Client> getClients() {
        return clientDomainService.findAll();
    }
}
