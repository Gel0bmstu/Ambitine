package com.example.networking.model.network;

import com.example.networking.model.LoginResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    /*
    Retrofit get annotation with our URL
    And our method that will return us the User
    */
    @GET("/")
    Call<LoginResponse> get();

    @FormUrlEncoded
    @POST("api/login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);
}
