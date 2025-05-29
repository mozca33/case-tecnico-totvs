package com.rafael.clients.domain.validator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.repository.PhoneRepository;

class PhoneValidatorTest {

    @Mock
    private PhoneRepository phoneRepository;

    private PhoneValidator validator;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        validator = new PhoneValidator(phoneRepository);
    }

    @Test
    void shouldThrowExceptionWhenPhoneIsInvalid() {
        Phone phone = mock(Phone.class);
        when(phone.isValid()).thenReturn(false);

        ClientException ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_PHONE_NUMBER);
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberIsNullOrBlank() {
        Phone phone = mock(Phone.class);
        when(phone.isValid()).thenReturn(true);

        when(phone.getPhoneNumber()).thenReturn(null);
        ClientException ex1 = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex1.getMessage()).isEqualTo(MessageConstants.PHONE_MUST_NOT_BE_BLANK);

        when(phone.getPhoneNumber()).thenReturn("   ");
        ClientException ex2 = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex2.getMessage()).isEqualTo(MessageConstants.PHONE_MUST_NOT_BE_BLANK);
    }

    @Test
    void shouldThrowExceptionWhenPhoneNumberAlreadyExists() {
        Phone phone = mock(Phone.class);
        when(phone.isValid()).thenReturn(true);
        when(phone.getPhoneNumber()).thenReturn("123456789");

        when(phoneRepository.existsByPhoneNumber("123456789")).thenReturn(true);

        ClientException ex = assertThrows(ClientException.class, () -> validator.validate(phone));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.PHONE_ALREADY_REGISTERED + "123456789");
    }

    @Test
    void shouldPassWhenPhoneIsValidAndNotRegistered() {
        Phone phone = mock(Phone.class);
        when(phone.isValid()).thenReturn(true);
        when(phone.getPhoneNumber()).thenReturn("987654321");

        when(phoneRepository.existsByPhoneNumber("987654321")).thenReturn(false);

        assertThatCode(() -> validator.validate(phone)).doesNotThrowAnyException();
    }
}
