package com.example.networking.model.network.Retrofit;

public class Api {
////    public static final String BASE_URL = "http://10.0.2.2:3000/";
//    public static final String BASE_URL = "http://192.168.100.32:9090/";
    public static final String BASE_URL = "http://35.228.98.103:9090/";
//    .baseUrl("http://35.228.98.103:9090/")
//    public static final String BASE_URL = "http://www.mocky.io/";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}

