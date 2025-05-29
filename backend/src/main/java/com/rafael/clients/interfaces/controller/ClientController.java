package com.rafael.clients.interfaces.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.clients.application.dto.ClientRequestDTO;
import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.application.mapper.ClientMapper;
import com.rafael.clients.application.service.ClientService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.getClients()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.getClientById(id)));
    }

    @PostMapping()
    public ResponseEntity<ClientResponseDTO> createClient(@Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.fromEntity(clientService.createClient(clientMapper.toEntity(clientRequest))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id,
            @Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.ok()
                .body(clientMapper
                        .fromEntity(clientService.updateClient(id, clientMapper.toEntity(id, clientRequest))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> patchClient(@PathVariable UUID id,
            @Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.updateClient(id, clientMapper.toEntity(clientRequest))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
