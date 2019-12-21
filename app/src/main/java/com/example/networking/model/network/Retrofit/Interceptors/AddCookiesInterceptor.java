package com.example.networking.model.network.Retrofit;

import android.util.Log;

import com.example.networking.model.UserRepository;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String authToken = UserRepository.getToken();
        if (!authToken.isEmpty()) {
            builder.addHeader("Set-Cookie", authToken);
        }
        return chain.proceed(builder.build());
    }
}