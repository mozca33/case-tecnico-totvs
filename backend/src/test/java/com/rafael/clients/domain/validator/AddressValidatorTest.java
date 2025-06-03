package com.rafael.clients.domain.validator;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Address;

class AddressValidatorTest {

    private AddressValidator validator;

    @BeforeEach
    void setup() {
        validator = new AddressValidator();
    }

    @Test
    void shouldThrowExceptionWhenAddressIsNull() {
        ClientException ex = assertThrows(ClientException.class, () -> validator.validate(null));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.ADDRESS_MUST_NOT_BE_NULL);
    }

    @Test
    void shouldThrowExceptionWhenStreetIsNullOrBlank() {
        Address address = validAddress();
        address.setStreet(null);
        ClientException ex1 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex1.getMessage()).isEqualTo(MessageConstants.STREET_MUST_NOT_BE_BLANK);

        address.setStreet("   ");
        ClientException ex2 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex2.getMessage()).isEqualTo(MessageConstants.STREET_MUST_NOT_BE_BLANK);
    }

    @Test
    void shouldThrowExceptionWhenCityIsNullOrBlank() {
        Address address = validAddress();
        address.setCity(null);
        ClientException ex1 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex1.getMessage()).isEqualTo(MessageConstants.CITY_MUST_NOT_BE_BLANK);

        address.setCity(" ");
        ClientException ex2 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex2.getMessage()).isEqualTo(MessageConstants.CITY_MUST_NOT_BE_BLANK);
    }

    @Test
    void shouldThrowExceptionWhenStateIsNullOrBlank() {
        Address address = validAddress();
        address.setState(null);
        ClientException ex1 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex1.getMessage()).isEqualTo(MessageConstants.STATE_MUST_NOT_BE_BLANK);

        address.setState("");
        ClientException ex2 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex2.getMessage()).isEqualTo(MessageConstants.STATE_MUST_NOT_BE_BLANK);
    }

    @Test
    void shouldThrowExceptionWhenZipCodeIsNullOrBlank() {
        Address address = validAddress();
        address.setZipCode(null);
        ClientException ex1 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex1.getMessage()).isEqualTo(MessageConstants.ZIPCODE_MUST_NOT_BE_BLANK);

        address.setZipCode(" ");
        ClientException ex2 = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex2.getMessage()).isEqualTo(MessageConstants.ZIPCODE_MUST_NOT_BE_BLANK);
    }

    @Test
    void shouldThrowExceptionWhenZipCodeFormatIsInvalid() {
        Address address = validAddress();
        address.setZipCode("1234-567"); // formato errado
        ClientException ex = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_ZIPCODE_FORMAT + "1234-567");

        address.setZipCode("abcde-123");
        ex = assertThrows(ClientException.class, () -> validator.validate(address));
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.INVALID_ZIPCODE_FORMAT + "abcde-123");
    }

    @Test
    void shouldPassWhenAddressIsValid() {
        Address address = validAddress();
        assertThatCode(() -> validator.validate(address)).doesNotThrowAnyException();
    }

    private Address validAddress() {
        Address address = new Address();
        address.setStreet("Rua das Flores");
        address.setCity("São Paulo");
        address.setState("SP");
        address.setZipCode("12345-678");
        address.setPublicPlace(null);
        address.setNumber("123L");
        address.setComplement("Qd 132 Lt 213");
        return address;
    }
}
