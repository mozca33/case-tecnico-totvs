package com.rafael.clients.domain.service.updater;

import org.springframework.stereotype.Component;

import com.rafael.clients.domain.model.Client;
import com.rafael.clients.domain.validator.AddressValidator;
import com.rafael.clients.domain.validator.PhoneValidator;

@Component
public class FullClientUpdater implements IClientUpdater {
    private final PhoneValidator phoneValidator;
    private final AddressValidator addressValidator;

    public FullClientUpdater(PhoneValidator phoneValidator, AddressValidator addressValidator) {
        this.phoneValidator = phoneValidator;
        this.addressValidator = addressValidator;
    }

    @Override
    public void update(Client existingClient, Client source) {
        Client other = (Client) source;
        existingClient.setName(other.getName());
        existingClient.setCpf(other.getCpf());

        other.getPhoneNumbers().forEach(phoneValidator::validate);
        existingClient.setPhoneNumbers(other.getPhoneNumbers());

        other.getAddresses().forEach(addressValidator::validate);
        existingClient.setAddresses(other.getAddresses());

    }
}
