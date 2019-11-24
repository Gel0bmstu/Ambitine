package com.example.networking.model.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.Required;

public class Promise {
    @SerializedName("userId")
    @Expose
    @Required
    private int userId;

    @SerializedName("content")
    @Expose
    @Required
    private String content;

    @SerializedName("endTime")
    @Expose
    @Required
    private Long endTime;
}
