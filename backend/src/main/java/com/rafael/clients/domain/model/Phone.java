package com.rafael.clients.domain.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "phones", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "phone_number" })
})
public class Phone {

    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    public Phone() {
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return this.id;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Phone))
            return false;
        Phone phone = (Phone) object;
        return phoneNumber.equals(phone.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    public boolean isValid() {
        return phoneNumber != null && phoneNumber.matches("^(?:\\+55)?[1-9]{2}9?\\d{8}$");
    }

    @PrePersist
    public void prePersist() {
        if (id == null)
            id = UUID.randomUUID();
    }
}
