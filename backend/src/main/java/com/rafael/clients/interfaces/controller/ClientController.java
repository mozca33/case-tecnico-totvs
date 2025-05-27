package com.rafael.clients.interfaces.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.application.mapper.ClientMapper;
import com.rafael.clients.application.service.ClientService;

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
        return ResponseEntity.ok().body(clientMapper.fromEntity(clientService.getClientById(id)));
    }
}
