package com.rafael.clients.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "clients", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "cpf" })
})
public class Client {

    @Id
    private UUID id;

    @Column(nullable = false)
    @Size(min = 10)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    public Client() {
    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Set<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setPhoneNumbers(Set<Phone> phones) {
        if (phones != null) {
            this.phoneNumbers.clear();
            this.phoneNumbers.addAll(phones);
        }

    }

    public void setAddresses(Set<Address> addresses) {
        if (addresses != null) {
            this.addresses.clear();
            this.addresses.addAll(addresses);
        }
    }

    public void addPhone(Phone phone) {
        phone.setClient(this);
        phoneNumbers.add(phone);
    }

    public void addAddress(Address address) {
        address.setClient(this);
        addresses.add(address);
    }

    public void mergeFrom(Client other) {
        if (other.getName() != null) {
            this.name = other.getName();
        }
        if (other.getCpf() != null) {
            this.cpf = other.getCpf();
        }

        if (other.getAddresses() != null) {
            for (Address address : other.getAddresses()) {
                address.setClient(this);
                this.addresses.add(address);
            }
        }

        if (other.getPhoneNumbers() != null) {
            for (Phone phone : other.getPhoneNumbers()) {
                phone.setClient(this);
                this.phoneNumbers.add(phone);
            }
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || this.getClass() != object.getClass())
            return false;
        Client client = (Client) object;
        return Objects.equals(this.id, client.id);
    }

    @PrePersist
    public void prePersist() {
        if (id == null)
            id = UUID.randomUUID();
    }
}
