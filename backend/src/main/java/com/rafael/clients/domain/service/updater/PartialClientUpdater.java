package com.rafael.clients.domain.service.updater;

import org.springframework.stereotype.Component;

import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.validator.AddressValidator;
import com.rafael.clients.domain.validator.PhoneValidator;

@Component
public class PartialClientUpdater implements IClientUpdater {
    private final PhoneValidator phoneValidator;
    private final AddressValidator addressValidator;

    public PartialClientUpdater(PhoneValidator phoneValidator, AddressValidator addressValidator) {
        this.phoneValidator = phoneValidator;
        this.addressValidator = addressValidator;
    }

    @Override
    public void update(Client existingClient, Client source) {
        Client other = (Client) source;
        if (other.getName() != null)
            existingClient.setName(other.getName());
        if (other.getCpf() != null)
            existingClient.setCpf(other.getCpf());

        if (other.getPhoneNumbers() != null && !other.getPhoneNumbers().isEmpty()) {
            other.getPhoneNumbers().forEach(phoneValidator::validate);
            existingClient.setPhoneNumbers(other.getPhoneNumbers());
        }

        if (other.getAddresses() != null && !other.getAddresses().isEmpty()) {
            other.getAddresses().forEach(addressValidator::validate);
            existingClient.setAddresses(other.getAddresses());
        }
    }
}
