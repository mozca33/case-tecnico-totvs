package com.rafael.clients.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.clients.domain.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    boolean existsByPhoneNumber(String number);
}
