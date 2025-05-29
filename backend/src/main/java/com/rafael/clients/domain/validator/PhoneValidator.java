package com.rafael.clients.domain.validator;

import org.springframework.stereotype.Component;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.PhoneRepository;

@Component
public class PhoneValidator {
    private final PhoneRepository phoneRepository;

    public PhoneValidator(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    /**
     * Validate the object of type {@link Phone}.
     * 
     * @param phone the object from type {@link Phone} to be validated.
     * @throws ClientException
     */
    public void validate(Phone phone) {
        if (!phone.isValid()) {
            throw new ClientException(MessageConstants.INVALID_PHONE_NUMBER);
        }
        if (phone.getPhoneNumber() == null || phone.getPhoneNumber().isBlank()) {
            throw new ClientException(MessageConstants.PHONE_MUST_NOT_BE_BLANK);
        }
        if (phoneRepository.existsByPhoneNumber(phone.getPhoneNumber())) {
            throw new ClientException(MessageConstants.PHONE_ALREADY_REGISTERED + phone.getPhoneNumber());
        }
    }
}
