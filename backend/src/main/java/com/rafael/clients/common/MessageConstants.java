package com.rafael.clients.common;

public class MessageConstants {

    public static final String NO_CLIENT_WAS_FOUND = "No client was found.";
    public static final String CLIENT_NOT_FOUND = "Client not found.";
    public static final String CLIENT_NOT_FOUND_WITH_ID = "Client not found with id: ";
    public static final String CLIENT_ALREADY_EXISTS = "Client already exists.";
    public static final String CLIENT_ALREADY_EXISTS_WITH_NAME = "Client already exists with this name";
    public static final String CLIENT_ALREADY_EXISTS_WITH_PHONE = "Client already exists with this phone";
    public static final String CLIENT_ALREADY_EXISTS_WITH_CPF = "Client already exists with this CPF";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number: ";
    public static final String INVALID_CPF = "Invalid cpf: ";
    public static final String NAME_CONSTRAINTS = "Client name should have between 10 and 255 characters.";
    public static final String PHONE_ALREADY_REGISTERED = "Phone already registered: ";
    public static final String PHONE_MUST_NOT_BE_BLANK = "Phone must not be blank.";
    public static final String ADDRESS_MUST_NOT_BE_NULL = "Address must not be null";
    public static final String STREET_MUST_NOT_BE_BLANK = "Street must not be blank";
    public static final String CITY_MUST_NOT_BE_BLANK = "City must not be blank";
    public static final String STATE_MUST_NOT_BE_BLANK = "State must not be blank";
    public static final String ZIPCODE_MUST_NOT_BE_BLANK = "ZipCode must not be blank";
    public static final String INVALID_ZIPCODE_FORMAT = "ZipCode format is invalid: ";

    private MessageConstants() {
    }
}
