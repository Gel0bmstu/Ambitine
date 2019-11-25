package com.example.networking.model.network.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("password")
    @Expose
    private String password;

    public RegistrationResponse(String email, String phone,
                         String nickname, String password) {
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.password = password;
    }
}
