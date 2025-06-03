package com.rafael.clients.domain.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.rafael.clients.common.MessageConstants;
import com.rafael.clients.domain.exception.ClientException;
import com.rafael.clients.domain.model.Phone;

@Service
public class NormalizerService {

    private static final Set<String> VALID_DDDS = new HashSet<>(Arrays.asList(
        "11", "12", "13", "14", "15", "16", "17", "18", "19", // SP
        "21", "22", "24",                                     // RJ/ES
        "27", "28",                                           // ES
        "31", "32", "33", "34", "35", "37", "38",             // MG
        "41", "42", "43", "44", "45", "46",                   // PR
        "47", "48", "49",                                     // SC
        "51", "53", "54", "55",                               // RS
        "61",                                                 // DF
        "62", "64",                                           // GO
        "63",                                                 // TO
        "65", "66",                                           // MT
        "67",                                                 // MS
        "68",                                                 // AC
        "69",                                                 // RO
        "71", "73", "74", "75", "77",                         // BA
        "79",                                                 // SE
        "81", "87",                                           // PE
        "82",                                                 // AL
        "83",                                                 // PB
        "84",                                                 // RN
        "85", "88",                                           // CE
        "86", "89",                                           // PI
        "91", "93", "94",                                     // PA
        "92", "97",                                           // AM
        "95",                                                 // RR
        "96",                                                 // AP
        "98", "99"                                            // MA
    ));

    /**
     * Normalizes a raw phone number, extracting its DDD and main number.
     * It handles international prefix (e.g., "+55") and removes non-digit characters.
     * Validates DDD and main number format according to Brazilian standards.
     *
     * @param phone The phone object containing the raw phone number
     * @return A Phone object with normalized DDD and main number
     * @throws ClientException if the phone number is invalid
     */
    public Phone normalize(Phone phone) {
        String rawPhoneNumber = phone.getPhoneNumber();
        
        if (rawPhoneNumber == null || rawPhoneNumber.isBlank()) {
            throw new ClientException(MessageConstants.PHONE_MUST_NOT_BE_BLANK);
        }

        String digitsOnly = rawPhoneNumber.replaceAll("[^0-9]", "");

        if (digitsOnly.startsWith("55") && digitsOnly.length() >= 12) {
            digitsOnly = digitsOnly.substring(2);
        }

        if (digitsOnly.length() < 10 || digitsOnly.length() > 11) {
            throw new ClientException(MessageConstants.PHONE_NUMBER_CONSTRAINT);
        }

        String ddd = digitsOnly.substring(0, 2);
        String mainNumber = digitsOnly.substring(2);

        if (!VALID_DDDS.contains(ddd)) {
            throw new ClientException(MessageConstants.INVALID_DDD + ": " + ddd);
        }

        validateMainNumber(mainNumber);

        phone.setDdd(ddd);
        phone.setMainNumber(mainNumber);

        return phone;
    }

    /**
     * Validates the main phone number according to Brazilian standards.
     * 
     * @param mainNumber The main phone number (without DDD)
     * @throws ClientException if the main number format is invalid
     */
    private void validateMainNumber(String mainNumber) {
        if (mainNumber.length() < 8 || mainNumber.length() > 9) {
            throw new ClientException(MessageConstants.INVALID_MAIN_NUMBER + mainNumber.length());
        }

        if (mainNumber.length() == 9 && !mainNumber.startsWith("9")) {
            throw new ClientException(MessageConstants.MOBILE_NUMBER_CONSTRAINT + mainNumber);
        }

        if (mainNumber.length() == 8 && mainNumber.startsWith("9")) {
            throw new ClientException(MessageConstants.LANDLINE_NUMBER_CONSTRAINT + mainNumber);
        }

        if (mainNumber.chars().distinct().count() == 1) {
            throw new ClientException(MessageConstants.MAIN_NUMBER_CONSTRAINT + mainNumber);
        }
    }
}