package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.ClientRequestDTO;
import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.domain.model.Client;

@Component
public class ClientMapper {

    public ClientMapper(final PhoneMapper phoneMapper, final AddressMapper addressMapper) {
    }

    /**
     * Converts a Set of {@link Client} entities to a set of
     * {@link ClientResponseDTO} objects.
     *
     * @param clients the set of {@link Client} entities to be converted
     * @return a set of {@link ClientResponseDTO} representing the given clients
     */
    public List<ClientResponseDTO> fromEntity(final List<Client> clients) {

        return clients.stream()
                .map(client -> new ClientResponseDTO(
                        client.getId(), client.getName(), client.getCpf(),
                        PhoneMapper.fromEntity(client.getPhoneNumber()),
                        AddressMapper.fromEntity(client.getAddresses())))
                .collect(Collectors.toList());

    }

    public ClientResponseDTO fromEntity(final Client client) {
        final ClientResponseDTO dto = new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                PhoneMapper.fromEntity(client.getPhoneNumber()),
                AddressMapper.fromEntity(client.getAddresses()));

        return dto;
    }

    public Client toEntity(final ClientRequestDTO dto) {
        final Client client = new Client(dto.name(), dto.cpf());

        if (dto.phones() != null) {
            PhoneMapper.toEntity(dto.phones());
        }

        if (dto.addresses() != null) {
            AddressMapper.toEntity(dto.addresses());
        }

        return client;
    }
}
