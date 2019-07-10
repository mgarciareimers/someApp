package com.mgarciareimers.someApp.commons;

public class Utilities {
    // Method that checks if the email is valid.
    public static boolean emailIsValid(String email) {
        return email != "";
    }

    // Method that checks if the password is valid.
    public static boolean passwordIsValid(String password) {
        return password.length() >= Constants.MIN_PASSWORD_LENGTH;
    }
}
