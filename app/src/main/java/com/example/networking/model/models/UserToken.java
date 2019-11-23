package com.example.networking.model.models;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class UserToken extends RealmObject {
    @Required
    private String username;

    public void setToken(final String username) {
        this.username = username;
    }
    public String getToken() {
        return username;
    }
}
