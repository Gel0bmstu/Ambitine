package com.example.networking.model.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User extends RealmObject {
    @SerializedName("nickname")
    @Expose
    @Required
    private String username;

    @SerializedName("nickname")
    @Expose
    @Required
    private String name;

    @SerializedName("imageId")
    @Expose
    @Required
    private String imageId;
}
