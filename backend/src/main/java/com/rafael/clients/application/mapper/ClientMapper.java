package com.rafael.clients.application.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rafael.clients.application.dto.ClientRequestDTO;
import com.rafael.clients.application.dto.ClientResponseDTO;
import com.rafael.clients.domain.model.Address;
import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.model.Phone;

@Component
public class ClientMapper {

    private final PhoneMapper phoneMapper;
    private final AddressMapper addressMapper;

    public ClientMapper(final PhoneMapper phoneMapper, final AddressMapper addressMapper) {
        this.phoneMapper = phoneMapper;
        this.addressMapper = addressMapper;
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
                        phoneMapper.fromEntity(client.getPhoneNumbers()),
                        addressMapper.fromEntity(client.getAddresses())))
                .collect(Collectors.toList());

    }

    /**
     * Converts a {@link Client} to a
     * {@link ClientResponseDTO} object.
     *
     * @param client of {@link Client} to be converted.
     * @return a {@link ClientResponseDTO} representing the given entity.
     */
    public ClientResponseDTO fromEntity(final Client client) {
        final ClientResponseDTO dto = new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getCpf(),
                phoneMapper.fromEntity(client.getPhoneNumbers()),
                addressMapper.fromEntity(client.getAddresses()));

        return dto;
    }

    /**
     * Converts a {@link ClientRequestDTO} to a
     * {@link Client} object.
     *
     * @param dto of {@link ClientRequestDTO} to be converted.
     * @return a {@link Client} representing the given dto.
     */
    public Client toEntity(final ClientRequestDTO dto) {
        final Client client = new Client(dto.name(), dto.cpf());

        if (dto.phones() != null) {
            List<Phone> phones = phoneMapper.toEntity(dto.phones());
            client.setPhoneNumbers(phones);
        }

        if (dto.addresses() != null) {
            List<Address> addresses = addressMapper.toEntity(dto.addresses());
            client.setAddresses(addresses);
        }

        return client;
    }

    public Client toEntity(final UUID id, final ClientRequestDTO dto) {
        final Client client = new Client(dto.name(), dto.cpf());
        client.setId(id);

        if (dto.phones() != null) {
            List<Phone> phones = phoneMapper.toEntity(dto.phones());
            client.getPhoneNumbers().clear();
            client.setPhoneNumbers(phones);
        }

        if (dto.addresses() != null) {
            List<Address> addresses = addressMapper.toEntity(dto.addresses());
            client.getAddresses().clear();
            client.setAddresses(addresses);
        }

        return client;
    }
}
