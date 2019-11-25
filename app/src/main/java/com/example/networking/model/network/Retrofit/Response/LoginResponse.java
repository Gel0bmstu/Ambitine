package com.example.networking.model.network.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("password")
    @Expose
    private String password;

    public LoginResponse(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
