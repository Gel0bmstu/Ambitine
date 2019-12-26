package com.example.networking.controller;

import android.widget.Toast;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.view.LoginActivity;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {
    private LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    public void onLoginButtonClick() {
        String username = loginActivity.getUsername();
        String password = loginActivity.getPassword();
        String token = loginActivity.getToken();
        LoginResponse loginResponse = new LoginResponse(username, password, token);
        login(loginResponse);
    }

    private void login(final LoginResponse loginResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.loginRequest(loginResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                int responseCode = response.code();
                if (responseCode== 201) {
                    loginActivity.SwitchActivityAfterLoginSuccess();
                }
            }
            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                String loginFailureMessage = loginActivity.getResources().getString(R.string.login_failure_case);
                AmbitinedToast.getInstance().debug(loginActivity, loginFailureMessage);
            }
        });
    }

}
