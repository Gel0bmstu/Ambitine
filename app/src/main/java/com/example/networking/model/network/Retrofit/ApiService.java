package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.User;
import com.example.networking.model.network.Retrofit.Response.FeedPromiseResponse;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/login")
    Call<ResponseBody> loginRequest(@Body LoginResponse loginResponse);

    @POST("api/signup")
    Call<ResponseBody> signUpRequest(@Body RegistrationResponse registrationResponse);

//    Нужно тестить
    @POST("api/user/{id}")
    Call<User> userResponse();
    // ToDo: Change url
    @POST("v2/5df635a63400009d17e5a540/")
    Call<List<FeedPromiseResponse>> feedResponse();
}
