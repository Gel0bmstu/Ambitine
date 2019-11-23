package com.example.networking.model;

import com.example.networking.model.database.UserDB;

public class UserRepository {
    public static Boolean InternetIsAvailable = false;

    public static void setToken(final String token) {
        UserDB.setToken(token);
    }

    public static String getToken() {
        return UserDB.getToken();
    }
}
