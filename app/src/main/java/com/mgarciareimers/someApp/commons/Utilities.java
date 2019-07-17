package com.mgarciareimers.someApp.commons;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Utilities {
    // Method that checks if the email is valid.
    public static boolean emailIsValid(String email) {
        return email != "";
    }

    // Method that checks if the password is valid.
    public static boolean passwordIsValid(String password) { return password.length() >= Constants.MIN_PASSWORD_LENGTH; }

    // Method that checks if the name is valid.
    public static boolean nameIsValid(String name) { return name != ""; }

    // Method that presents a toast.
    public static void presentToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
