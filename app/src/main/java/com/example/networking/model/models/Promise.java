package com.example.networking.model.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.annotations.Required;

public class Promise {
    private String username;
    private String img_url;
    private String promise_description;
    private Long pastdue;
    private Integer deposit;
    private Boolean accepted;

    public String getUsername() {
        return this.username;
    }


    public String getImg_url() {
        return img_url;
    }


    public Integer getDeposit() {
        return deposit;
    }
}
