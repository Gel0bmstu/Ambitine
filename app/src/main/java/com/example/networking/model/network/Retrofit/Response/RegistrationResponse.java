package com.example.networking.model.network.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("token")
    @Expose
    private String token;

    public RegistrationResponse(String nickname, String password, String token) {
        setNickname(nickname);
        setPassword(password);
        setToken(token);
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
