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
    private Set<Phone> phoneNumber = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    protected Client() {
    }

    public Client(String name, String cpf) {
        this.id = UUID.randomUUID();
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

    public Set<Phone> getPhoneNumber() {
        return phoneNumber;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void addPhone(Phone phone) {
        phone.setClient(this);
        phoneNumber.add(phone);
    }

    public void addAddress(Address address) {
        address.setClient(this);
        addresses.add(address);
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

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
