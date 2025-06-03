package com.rafael.clients.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;

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

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Phone> phoneNumbers = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

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

    public List<Phone> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<Address> getAddresses() {
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

    public void setPhoneNumbers(List<Phone> phones) {
        this.phoneNumbers.clear();
        
        if (phones != null) {
            for (Phone phone : phones) {
                addPhone(phone);
            }
        }
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses.clear();
        
        if (addresses != null) {
            for (Address address : addresses) {
                addAddress(address);
            }
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
            this.getAddresses().clear();
            this.getAddresses().addAll(other.getAddresses());
        }

        if (other.getPhoneNumbers() != null) {
            this.getPhoneNumbers().clear();
            this.getPhoneNumbers().addAll(other.getPhoneNumbers());
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