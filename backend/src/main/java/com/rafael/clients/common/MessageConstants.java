package com.rafael.clients.common;

public class MessageConstants {

    public static final String NO_CLIENT_WAS_FOUND = "No client was found.";
    public static final String CLIENT_NOT_FOUND = "Client not found.";
    public static final String CLIENT_NOT_FOUND_WITH_ID = "Client not found with id";
    public static final String CLIENT_ALREADY_EXISTS = "Client already exists.";
    public static final String CLIENT_ALREADY_EXISTS_WITH_NAME = "Client already exists with this name";
    public static final String CLIENT_ALREADY_EXISTS_WITH_PHONE = "Client already exists with this phone";
    public static final String CLIENT_ALREADY_EXISTS_WITH_CPF = "Client already exists with this CPF";

    public static final String INVALID_CPF = "Invalid cpf: ";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number: ";
    public static final String INVALID_ZIPCODE_FORMAT = "ZipCode format is invalid: ";
    public static final String INVALID_MAIN_NUMBER = "Main number must have 8 or 9 digits. Received: ";
    public static final String INVALID_DDD = "Invalid DDD. The number must begin with a valid brazilian DDD (ex: 11, 21, 31, etc.).";

    public static final String PHONE_NUMBER_CONSTRAINT = "Invalid phone number, try again";
    public static final String CPF_CONSTRAINT = "Invalid cpf, try again";
    public static final String NAME_CONSTRAINT = "Client name should have between 10 and 255 characters.";
    public static final String MOBILE_NUMBER_CONSTRAINT = "Mobile numbers (9 digits) must start with 9. Received: ";
    public static final String LANDLINE_NUMBER_CONSTRAINT = "Landline numbers (8 digits) cannot start with 9. Received: ";
    public static final String MAIN_NUMBER_CONSTRAINT = "Main number cannot have all identical digits: ";

    public static final String PHONE_MUST_NOT_BE_BLANK = "Phone must not be blank";
    public static final String STREET_MUST_NOT_BE_BLANK = "Street must not be blank";
    public static final String CITY_MUST_NOT_BE_BLANK = "City must not be blank";
    public static final String STATE_MUST_NOT_BE_BLANK = "State must not be blank";
    public static final String ZIPCODE_MUST_NOT_BE_BLANK = "ZipCode must not be blank";
    public static final String COMPLEMENT_MUST_NOT_BE_BLANK = "Complement must not be blank";
    public static final String MAIN_NUMBER_MUST_NOT_BE_BLANK = "Main number must not be blank.";
    public static final String CPF_MUST_NOT_BE_BLANK = "CPF must not be blank.";
    public static final String DDD_MUST_NOT_BE_BLANK = "DDD must not be blank.";

    public static final String ADDRESS_MUST_NOT_BE_EMPTY = "Address list must not be empty.";
    public static final String PHONE_MUST_NOT_BE_EMPTY = "Phone list must not be empty.";

    public static final String ADDRESS_MUST_NOT_BE_NULL = "Address must not be null";
    public static final String PHONE_ALREADY_REGISTERED = "Phone already registered: ";
    public static final String MAIN_NUMBER_MUST_BE_NUMERIC = "Main number must be numeric.";

    private MessageConstants() {
    }
}