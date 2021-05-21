package com.abualrub.androidassignmentonegroup.utils;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
    TRUE ---> VALID
    FALSE ---> NOT VALID
    first name:
    1- "[a-zA-Z]+" (only english characters)
    2-  2 <= length < 15

    last name:
    1- "[a-zA-Z]+" (only english characters)
    2-  2 <= length <= 15

    username:
    1- 3 <= length <= 30

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

// *********************************
// MADE BY OSID ABU-ALRUB (1183096)
// *********************************
public class Validator {
    private static final String NAME_REGEX = "[a-zA-Z]+";
    private static final int NAME_MIN_LENGTH = 2;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 30;
    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,20}$";
    private static final String EMAIL_REGEX = "^(.+)@(.+)$";
    private static final int PHONE_NUMBER_LENGTH = 10;

    private AppCompatActivity activity;
    public Validator(AppCompatActivity activity){
        this.activity = activity;
    }

    public boolean isValidName(String name){
        name = name.trim();
        if(name.matches(NAME_REGEX) && (name.length() >= NAME_MIN_LENGTH && name.length() <= NAME_MAX_LENGTH)){
            return true;
        }
        showToast("Must contain english letters only");
        return false;
    }

    public boolean isValidUserName(String userName){
        userName = userName.trim();
        if((userName.length() >= USERNAME_MIN_LENGTH && userName.length() <= USERNAME_MAX_LENGTH)) {
            return true;
        }
        showToast("Length must be between 3 and 30");
        return false;
    }

    public boolean isValidPassword(String password){
        password = password.trim();
        if(password.matches(PASSWORD_REGEX)){
            return true;
        }
        showToast("Must have 1 numeric, 1 lowcase, 1 uppercase, and length >= 5");
        return false;
    }

    public boolean isValidEmail(String email){
        email = email.trim();
        if(!email.isEmpty() && email.matches(EMAIL_REGEX)){
            return true;
        }
        showToast("Make sure email is valid");
        return false;
    }

    public boolean isValidPhoneNumber(String phoneNumber){
        phoneNumber.trim();
        if(phoneNumber.length() == PHONE_NUMBER_LENGTH){
            return true;
        }
        showToast("Only numbers and length must equal 10");
        return false;
    }

    private void showToast(String str){
        Toast.makeText(this.activity, str, Toast.LENGTH_LONG).show();
    }
}
