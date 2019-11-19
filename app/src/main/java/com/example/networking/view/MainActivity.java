package com.example.networking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.networking.R;
import com.example.networking.model.LoginResponse;
import com.example.networking.model.network.Api;
import com.example.networking.model.network.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;

    public static MainActivity getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        LoginResponse loginResponse = new LoginResponse("hello", "pidor");
        login(loginResponse);
    }

    private void login(LoginResponse loginResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.loginRequest(loginResponse.getUsername(),
                loginResponse.getPassword());
        Log.d("WHAT", loginResponse.getPassword());
        Log.d("WHAT", loginResponse.getUsername());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String cookie1 = response.headers().get("Set-Cookie");
                Log.d("WHAT", cookie1);
                if (response.code() == 201) {
                    String cookie = response.headers().get("Set-Cookie");
                    Log.d("WHAT", cookie);
                } else {
                    Log.d("WHAT", "smth get wrong!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("WHAT ", t.getMessage());
            }
        });
    }
}
