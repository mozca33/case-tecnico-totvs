package com.rafael.clients.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.clients.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, UUID id);

    boolean existsByCpfAndIdNot(String cpf, UUID id);
    
    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByNameAndIdNot(String name, UUID id);
}
