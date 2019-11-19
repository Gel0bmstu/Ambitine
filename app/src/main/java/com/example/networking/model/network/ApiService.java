package com.example.networking.model.network;

import com.example.networking.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    /*
    Retrofit get annotation with our URL
    And our method that will return us the User
    */
    @GET("/")
    Call<LoginResponse> get();

    @POST("api/login")
    Call<ResponseBody> loginRequest(@Body LoginResponse loginResponse);
}
