package com.example.networking.model.network.Retrofit.Interceptors;

import com.example.networking.model.UserRepository;
import com.example.networking.model.database.UserDB;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AddCookiesInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String authToken = UserRepository.getToken();
        if (!authToken.equals(UserDB.TOKEN_NOT_FOUND)) {
            builder.addHeader("Cookie", authToken);
        }
        return chain.proceed(builder.build());
    }
}