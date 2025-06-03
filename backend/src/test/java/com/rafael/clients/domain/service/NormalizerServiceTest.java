package com.rafael.clients.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Phone;

class NormalizerServiceTest {

    private NormalizerService normalizerService;

    @BeforeEach
    void setup() {
        normalizerService = new NormalizerService();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t", "\n", "  \t\n  "})
    void shouldThrowExceptionWhenPhoneNumberIsBlank(Phone blankPhone) {
        ClientException ex = assertThrows(ClientException.class, 
            () -> normalizerService.normalize(blankPhone));
        
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.PHONE_MUST_NOT_BE_BLANK);
    }

    // Testes para números com prefixo internacional (+55)
    @Test
    void shouldNormalizePhoneNumberWithInternationalPrefix() {
        String rawPhone = "+5511999998888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNormalizePhoneNumberWithInternationalPrefixWithoutPlus() {
        String rawPhone = "5511999998888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNormalizePhoneNumberWithInternationalPrefixAndFixedLine() {
        String rawPhone = "+551133334444";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("33334444");
    }

    // Testes para números sem prefixo internacional
    @Test
    void shouldNormalizePhoneNumberWithoutInternationalPrefix() {
        String rawPhone = "11999998888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNormalizeFixedLinePhoneNumber() {
        String rawPhone = "1133334444";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("33334444");
    }

    // Testes para números com formatação especial
    @Test
    void shouldNormalizePhoneNumberWithSpecialCharacters() {
        String rawPhone = "+55 (11) 99999-8888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNormalizePhoneNumberWithDots() {
        String rawPhone = "11.99999.8888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNormalizePhoneNumberWithSpaces() {
        String rawPhone = "11 9 9999 8888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }


    @Test
    void shouldNotRemove55WhenItIsPartOfgetDdd() {
        String rawPhone = "5511999998888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldNotRemove55WhenPhoneHasExactly11Digits() {
        String rawPhone = "55987654321";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("55");
        assertThat(result.getMainNumber()).isEqualTo("987654321");
    }

    @Test
    void shouldThrowExceptionWhenPhoneContainsOnlySpecialCharacters() {
        String rawPhone = "()+-. ";
        Phone phone = new Phone(rawPhone);
        
        ClientException ex = assertThrows(ClientException.class, 
            () -> normalizerService.normalize(phone));
        
        assertThat(ex.getMessage()).isEqualTo(MessageConstants.PHONE_NUMBER_CONSTRAINT);
    }

    @Test
    void shouldNormalizeMinimumValidPhoneNumber() {
        String rawPhone = "1133334444";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("33334444");
    }

    @Test
    void shouldNormalizeMaximumValidPhoneNumber() {
        String rawPhone = "11999998888"; 
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }

    @Test
    void shouldRemove55OnlyWhenPhoneHas12OrMoreDigits() {
        String phone12Digits = "557989998888";
        Phone phone = new Phone(phone12Digits);
        

        Phone result12 = normalizerService.normalize(phone);
        assertThat(result12.getDdd()).isEqualTo("79");
        assertThat(result12.getMainNumber()).isEqualTo("89998888");
        
        String phone13Digits = "5511999998888";
        Phone phone2 = new Phone(phone13Digits);

        Phone result13 = normalizerService.normalize(phone2);
        assertThat(result13.getDdd()).isEqualTo("11");
        assertThat(result13.getMainNumber()).isEqualTo("999998888");
    }

    // Teste para números com letras (devem ser removidas)
    @Test
    void shouldRemoveLettersFromPhoneNumber() {
        String rawPhone = "11abc999def998ghi888";
        Phone phone = new Phone(rawPhone);
        
        Phone result = normalizerService.normalize(phone);
        
        assertThat(result.getDdd()).isEqualTo("11");
        assertThat(result.getMainNumber()).isEqualTo("999998888");
    }
}