package com.abualrub.androidassignmentonegroup.utils;
/*
    TRUE ---> VALID
    FALSE ---> NOT VALID
    first name:
    1- "[a-zA-Z]+" (only english characters)
    2-  1 < length < 15

    last name:
    1- "[a-zA-Z]+" (only english characters)
    2-  1 < length < 15

    username:
    1- 3 < length < 30

    password:
    1- ^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20}$
     1.1- must have at least one numeric number
     1.2- must have at least one lower case character
     1.3- must have at least one uppercase character
     1.4- 5 <= length <= 20
     1.5 Reference https://java2blog.com/validate-password-java/

    email:
    1- ^(.+)@(.+)$ (must have characters before and after the @)
     1.1 Reference https://howtodoinjava.com/java/regex/java-regex-validate-email-address/

    Phone number:
    1- length = 10
 */
public class Validator {
    private static final String NAME_REGEX = "[a-zA-Z]+";
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 30;
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20}$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final int PHONE_NUMBER_LENGTH = 10;

    public boolean isValidName(String name){
        name = name.trim();
        return name.matches(NAME_REGEX) && (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH);
    }

    public boolean isValidUserName(String userName){
        userName = userName.trim();
        return (userName.length() >= USERNAME_MIN_LENGTH && userName.length() <= USERNAME_MAX_LENGTH);
    }

    public boolean isValidPassword(String password){
        password = password.trim();
        return password.matches(PASSWORD_REGEX);
    }

    public boolean isValidEmail(String email){
        email = email.trim();
        return !email.isEmpty() && email.matches(EMAIL_REGEX);
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        phoneNumber.trim();
        return phoneNumber.length() == PHONE_NUMBER_LENGTH;
    }
}
