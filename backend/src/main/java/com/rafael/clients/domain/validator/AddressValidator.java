package com.rafael.clients.domain.validator;

import org.springframework.stereotype.Component;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Address;

@Component
public class AddressValidator {
    public void validate(Address address) {
        if (address == null) {
            throw new ClientException(MessageConstants.ADDRESS_MUST_NOT_BE_NULL);
        }

        if (address.getStreet() == null || address.getStreet().isBlank()) {
            throw new ClientException(MessageConstants.STREET_MUST_NOT_BE_BLANK);
        }

        if (address.getCity() == null || address.getCity().isBlank()) {
            throw new ClientException(MessageConstants.CITY_MUST_NOT_BE_BLANK);
        }

        if (address.getState() == null || address.getState().isBlank()) {
            throw new ClientException(MessageConstants.STATE_MUST_NOT_BE_BLANK);
        }

        if (address.getZipCode() == null || address.getZipCode().isBlank()) {
            throw new ClientException(MessageConstants.ZIPCODE_MUST_NOT_BE_BLANK);
        }

        // Validation for Brazilian Zip code
        if (!address.getZipCode().matches("^\\d{5}-?\\d{3}$")) {
            throw new ClientException(MessageConstants.INVALID_ZIPCODE_FORMAT + address.getZipCode());
        }

    }
}
