package com.example.networking.conroller;

import android.util.Log;

import com.example.networking.R;
import com.example.networking.model.UserRepository;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.view.LoginActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {
    private static String logTag;

    private LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
        logTag = loginActivity.getApplicationContext().getResources().getString(R.string.logTag);
    }

    public void onLoginButtonClick() {
        String username = loginActivity.getUsername();
        String password = loginActivity.getPassword();
        LoginResponse loginResponse = new LoginResponse(username, password);
        login(loginResponse);
    }

    private void login(final LoginResponse loginResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.loginRequest(loginResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    String authToken = response.headers().get("Set-Cookie");

                    UserRepository.setToken(authToken);

                    loginActivity.SwitchActivityAfterLoginSuccess();

                    Log.d(logTag, UserRepository.getToken());
                    Log.d(logTag, authToken);
                } else {
                    Log.d(logTag, "smth get wrong!");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(logTag, t.getMessage());
            }
        });
    }

}
