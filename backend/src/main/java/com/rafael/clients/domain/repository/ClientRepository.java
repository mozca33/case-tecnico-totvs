package com.rafael.clients.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.clients.domain.model.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    boolean existsByCpf(String cpf);

    boolean existsByName(String name);

    Optional<Client> findByCpf(String cpf);

    Optional<Client> findByName(String name);
}
