package com.rafael.clients.application.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rafael.clients.application.dto.PhoneDTO;
import com.rafael.clients.domain.model.Phone;
import com.rafael.clients.domain.service.NormalizerService;

class PhoneMapperTest {

    private PhoneMapper phoneMapper;
    private NormalizerService normalizerService = new NormalizerService();

    @BeforeEach
    void setUp() {
        phoneMapper = new PhoneMapper(normalizerService);
    }

    @Test
    void shouldMapPhoneEntitiesToPhoneDTOs() {
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("+55123456789"));
        phones.add(new Phone("987654321"));

        List<PhoneDTO> result = phoneMapper.fromEntity(phones);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(PhoneDTO::number)
                .containsExactlyInAnyOrder("987654321", "+55123456789");
    }

    @Test
    void shouldMapPhoneDTOsToPhoneEntities() {
        List<PhoneDTO> dtos = new ArrayList<>();
        dtos.add(new PhoneDTO("(11) 99999-8888")); 
        dtos.add(new PhoneDTO("(21) 98765-4321"));

        List<Phone> result = phoneMapper.toEntity(dtos);

        assertThat(result).hasSize(2);
        
        assertThat(result).extracting(Phone::getDdd)
                .containsExactlyInAnyOrder("11", "21");
        assertThat(result).extracting(Phone::getMainNumber)
                .containsExactlyInAnyOrder("999998888", "987654321");
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
