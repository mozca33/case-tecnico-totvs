package com.rafael.clients.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.service.ClientDomainService;

@Service
public class ClientService {

    private final ClientDomainService clientDomainService;

    public ClientService(ClientDomainService clientDomainService) {
        this.clientDomainService = clientDomainService;
    }

    public Client createClient(Client client) {
        return clientDomainService.createClient(client);
    }

    public Client getClientById(UUID id) {
        return clientDomainService.findById(id);
    }

    public List<Client> getClients() {
        return clientDomainService.findAll();
    }

    public Client updateClient(UUID id, Client entity) {
        return clientDomainService.updateClient(id, entity);
    }

    public void deleteClient(UUID id) {
        clientDomainService.deleteClient(id);
    }
}
