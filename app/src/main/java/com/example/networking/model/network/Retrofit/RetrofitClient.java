package com.example.networking.model.network.Retrofit;

import com.example.networking.model.network.Retrofit.Interceptors.AddCookiesInterceptor;
import com.example.networking.model.network.Retrofit.Interceptors.ReceivedCookiesInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor).
                addInterceptor(new AddCookiesInterceptor()).
                addInterceptor(new ReceivedCookiesInterceptor()).
                build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
