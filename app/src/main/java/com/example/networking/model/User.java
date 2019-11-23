package com.example.networking.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User extends RealmObject {
    @Required
    private String username;

    @Required
    private String name;
}
