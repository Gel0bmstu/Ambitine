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

    public RegistrationResponse(String nickname, String password) {
        setNickname(nickname);
        setPassword(password);
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
