package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.User;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/signin")
    Call<ResponseBody> loginRequest(@Body LoginResponse loginResponse);

    @POST("api/signup")
    Call<ResponseBody> signUpRequest(@Body RegistrationResponse registrationResponse);

//    Нужно тестить
    @POST("api/user/{id}")
    Call<User> userRequest();
    // ToDo: Change url

    @POST("api/logout")
    Call<ResponseBody> logoutRequest();
}


