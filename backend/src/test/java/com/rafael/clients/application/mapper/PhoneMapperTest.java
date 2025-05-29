package com.rafael.clients.application.mapper;

import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneMapperTest {

    private PhoneMapper phoneMapper;

    @BeforeEach
    void setUp() {
        phoneMapper = new PhoneMapper();
    }

    @Test
    void shouldMapPhoneEntitiesToPhoneDTOs() {
        Set<Phone> phones = Set.of(
                new Phone("123456789"),
                new Phone("987654321"));

        Set<PhoneDTO> result = phoneMapper.fromEntity(phones);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(PhoneDTO::number)
                .containsExactlyInAnyOrder("123456789", "987654321");
    }

    @Test
    void shouldMapPhoneDTOsToPhoneEntities() {
        Set<PhoneDTO> dtos = Set.of(
                new PhoneDTO("123456789"),
                new PhoneDTO("987654321"));

        Set<Phone> result = phoneMapper.toEntity(dtos);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(Phone::getPhoneNumber)
                .containsExactlyInAnyOrder("123456789", "987654321");
    }

    @Test
    void fromEntityShouldReturnNullWhenInputIsNull() {
        assertThat(phoneMapper.fromEntity(null)).isNull();
    }

    @Test
    void toEntityShouldReturnNullWhenInputIsNull() {
        assertThat(phoneMapper.toEntity(null)).isNull();
    }
}
