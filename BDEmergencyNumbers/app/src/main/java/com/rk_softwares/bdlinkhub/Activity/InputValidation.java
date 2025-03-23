package com.rk_softwares.bdlinkhub.Activity;

import android.util.Patterns;

import java.util.regex.Pattern;

public class InputValidation {


    public static boolean isValidUsername(String username) {

        if (username == null || username.trim().isEmpty()){

            return false;

        }

        return username.trim().matches("^[a-zA-Z0-9_-]+$");
    }


    //Email validation
    public static boolean isValidEmail(String email){

        if (email == null || email.isEmpty()){

            return false;

        }
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();

    }

    //Password validation
    public static boolean isValidPassword(String password) {

        if (password == null || password.trim().isEmpty() || password.length() < 12) {

            return false;

        }

        return true;
    }

    //input sanitization
    public static String filterInput(String input){

        if (input == null) return "";

        return input.trim().replaceAll("[^a-zA-Z0-9@._-]", "");

    }

}
