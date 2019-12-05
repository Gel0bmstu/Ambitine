package com.example.networking.model.models;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class UserToken extends RealmObject {
    @Required
    private String token;

    public void setToken(final String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
