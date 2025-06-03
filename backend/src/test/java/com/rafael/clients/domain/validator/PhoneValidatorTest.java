package com.rafael.clients.domain.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.PhoneRepository;
import com.rafael.clients.domain.service.NormalizerService;

public class PhoneValidatorTest {

    private PhoneValidator validator;
    private PhoneRepository phoneRepository;
    private NormalizerService normalizerService;

    @BeforeEach
    void setUp() {
        phoneRepository = mock(PhoneRepository.class);
        validator = new PhoneValidator(phoneRepository);
        normalizerService = mock(NormalizerService.class);
    }
    
    @Test
    void shouldThrowException_WhenNumberTooShort() {
        Phone phone = new Phone();
        phone.setPhoneNumber("12345");

        when(normalizerService.normalize(phone)).thenReturn(null);

        Exception ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_PHONE_NUMBER + phone.getPhoneNumber());
    }

    @Test
    void shouldThrowException_WhenNumberTooLong() {
        Phone phone = new Phone();
        phone.setPhoneNumber("551199123456789");

        when(normalizerService.normalize(phone)).thenReturn(null);

        Exception ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_PHONE_NUMBER + phone.getPhoneNumber());
    }

    @Test
    void shouldThrowException_WhenPhoneNumberNull() {
        Phone phone = new Phone();
        phone.setPhoneNumber(null);

        Exception ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_PHONE_NUMBER + phone.getPhoneNumber());
    }

    @Test
    void shouldThrowException_WhenPhoneNumberBlank() {
        Phone phone = new Phone();
        phone.setPhoneNumber("   ");

        Exception ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_PHONE_NUMBER + phone.getPhoneNumber());
    }
}