package com.example.networking.controller;


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
    private String VALIDATED_OK = "OK";

    private LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }


    public void onLoginButtonClick() {
        String username = loginActivity.getUsername();
        String password = loginActivity.getPassword();
        String token = loginActivity.getToken();

        String error = validateUsername(username);
        if (!error.equals(VALIDATED_OK)) {
            loginActivity.printError(error);
            return;
        }
        error = validatePassword(password);
        if (!error.equals(VALIDATED_OK)) {
            loginActivity.printError(error);
            return;
        }

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
                if (responseCode == 201) {
                    loginActivity.SwitchActivityAfterLoginSuccess();
                } else if (responseCode == 400) {
                    String loginFailureMessage = loginActivity.getResources().getString(R.string.login_wrong_data);
                    AmbitinedToast.getInstance().debugDarkColor(loginActivity, loginFailureMessage);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                String loginFailureMessage = loginActivity.getResources().getString(R.string.login_failure_case);
                AmbitinedToast.getInstance().debugRedColor(loginActivity, loginFailureMessage);
            }
        });
    }

    private String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Please, fill in the username field";
        }
        return VALIDATED_OK;
    }

    private String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Please, fill in the password field";
        }
        return VALIDATED_OK;
    }
}
