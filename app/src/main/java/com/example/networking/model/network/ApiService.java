package com.example.networking.model.network;

import com.example.networking.model.network.Response.LoginResponse;
import com.example.networking.model.network.Response.RegistrationResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    Call<ResponseBody> loginRequest(@Body LoginResponse loginResponse);

    @POST("api/signup")
    Call<ResponseBody> signupRequest(@Body RegistrationResponse registrationResponse);
}
