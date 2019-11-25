package com.example.networking.model.network.Retrofit;

public class Api {
    public static final String BASE_URL = "http://10.0.2.2:3000/";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
