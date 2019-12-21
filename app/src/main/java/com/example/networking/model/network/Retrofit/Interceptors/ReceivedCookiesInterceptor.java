package com.example.networking.model.network.Retrofit.Interceptors;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.networking.model.UserRepository;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    @Override @NonNull
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        String authToken = originalResponse.header("Set-Cookie");
        if (authToken != null) {
            if (!authToken.isEmpty()) {
                UserRepository.setToken(authToken);
            }
            Log.d("hellomelloy huinya", authToken);
        }
        return originalResponse;
    }
}
