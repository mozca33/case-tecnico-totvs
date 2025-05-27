package com.rafael.clients.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.clients.domain.model.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

    boolean existsByPhoneNumber(String number);
}
