package com.example.networking.model.network;

import com.example.networking.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    /*
    Retrofit get annotation with our URL
    And our method that will return us the User
    */
    @GET("/")
    Call<User> get();
}
