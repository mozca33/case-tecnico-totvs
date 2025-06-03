package com.rafael.clients.interfaces.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clients")
@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @Operation(summary = "Lista todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.getClients()));
    }

     @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })

    @Operation(
        summary = "Busca cliente por ID",
        description = "Retorna os dados do cliente de acordo com o ID informado."
    )
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@Parameter(description = "ID do cliente (UUID)", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.getClientById(id)));
    }


    @Operation(
        summary = "Cria um novo cliente",
        description = "Adiciona um novo cliente ao sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Cliente criado com sucesso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ClientResponseDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Erro de validação",
            content = @Content
        )
    })
    @PostMapping()
    public ResponseEntity<ClientResponseDTO> createClient(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados para criar cliente",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ClientRequestDTO.class))
            )@Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientMapper.fromEntity(clientService.createClient(clientMapper.toEntity(clientRequest))));
    }

    @Operation(summary = "Atualiza totalmente um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@Parameter(description = "ID do cliente (UUID)", required = true) @PathVariable UUID id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Dados para atualizar o cliente",
                required = true,
                content = @Content(schema = @Schema(implementation = ClientRequestDTO.class))
            )@Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.ok()
                .body(clientMapper
                        .fromEntity(clientService.updateClient(id, clientMapper.toEntity(id, clientRequest))));
    }

    @Operation(summary = "Atualiza parcialmente um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente parcialmente atualizado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> patchClient(@Parameter(description = "ID do cliente (UUID)", required = true) @PathVariable UUID id,
            @Valid @RequestBody ClientRequestDTO clientRequest) {
        return ResponseEntity.ok()
                .body(clientMapper.fromEntity(clientService.updateClient(id, clientMapper.toEntity(clientRequest))));
    }

    @Operation(summary = "Remove um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@Parameter(description = "ID do cliente (UUID)", required = true) @PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
