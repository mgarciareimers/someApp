package com.mgarciareimers.someApp.model;

import android.graphics.Bitmap;

import java.util.List;

public class UserPictureGroup {
    private List<User> userList;
    private List<Bitmap> bitmapList;

    public UserPictureGroup(List<User> userList, List<Bitmap> bitmapList) {
        this.userList = userList;
        this.bitmapList = bitmapList;
    }

    public List<User> getUserList() {
        return this.userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Bitmap> getBitmapList() {
        return this.bitmapList;
    }

    public void setBitmapList(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
    }
}
