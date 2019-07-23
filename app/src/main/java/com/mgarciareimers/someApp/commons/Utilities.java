package com.mgarciareimers.someApp.commons;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.widget.Toast;

import com.mgarciareimers.someApp.model.User;
import com.mgarciareimers.someApp.model.UserPictureGroup;

import java.util.ArrayList;
import java.util.List;

public class Utilities {
    // Method that checks if the email is valid.
    public static boolean emailIsValid(String email) {
        return email != "";
    }

    // Method that checks if the password is valid.
    public static boolean passwordIsValid(String password) { return password.length() >= Constants.MIN_PASSWORD_LENGTH; }

    // Method that checks if the name is valid.
    public static boolean nameIsValid(String name) {
        return !name.equals("");
    }

    // Method that presents a toast.
    public static void presentToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    // Method that filters the users from the filter input.
    public static UserPictureGroup filterUsers(String filter, List<User> userList, List<Bitmap> bitmapList) {
        if (!filter.equals("") && userList.size() == bitmapList.size()) {
            List<User> filteredUserList = new ArrayList<>();
            List<Bitmap> filteredBitmapList = new ArrayList<>();

            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getName().contains(filter) || userList.get(i).getEmail().contains(filter) ||
                    userList.get(i).getPhone().contains(filter) || userList.get(i).getWebsite().contains(filter) ||
                    userList.get(i).getAddress().containsFilter(filter) || userList.get(i).getCompany().containsFilter(filter)) {

                    filteredUserList.add(userList.get(i));
                    filteredBitmapList.add(bitmapList.get(i));
                }
            }

            return new UserPictureGroup(filteredUserList, filteredBitmapList);
        } else {
            return new UserPictureGroup(userList, bitmapList);
        }
    }
}
