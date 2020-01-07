package com.example.networking.controller;

import android.util.Log;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;
import com.example.networking.view.SignUpActivity;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TransferQueue;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpController {
    private String VALIDATED_OK = "OK";

    private SignUpActivity signUpActivity;

    public SignUpController(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    public void onSignUpClick() {
        String username = signUpActivity.getUsername();
        String password = signUpActivity.getPassword();
        String token = signUpActivity.getToken();

        String error = validateUsername(username);
        if (!error.equals(VALIDATED_OK)) {
            signUpActivity.printError(error);
            return;
        }
        error = validatePassword(password);
        if (!error.equals(VALIDATED_OK)) {
            signUpActivity.printError(error);
            return;
        }

        RegistrationResponse registrationResponse = new RegistrationResponse(username, password, token);
        signUp(registrationResponse);
    }

    private void signUp(final RegistrationResponse registrationResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.signUpRequest(registrationResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                int responseCode = response.code();
                if (responseCode == 201) {
                    signUpActivity.SwitchActivityAfterSignUpSuccess();
                } else if (responseCode == 409) {
                    String userConflictMessage = signUpActivity.getResources().getString(R.string.profile_data_error);
                    AmbitinedToast.getInstance().debugAboveTheKeyboard(signUpActivity, userConflictMessage);
                }

            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                String userConflictMessage = signUpActivity.getResources().getString(R.string.signup_failure_case);
                AmbitinedToast.getInstance().debugAboveTheKeyboard(signUpActivity, userConflictMessage);
            }
        });
    }

    private String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Please, fill in the username field";
        }
        String workLine = username.trim();
        if (workLine.indexOf(' ') > 0) {
            return "Please, remove spaces from username";
        }
        if (workLine.length() < 4) {
            return "Username is too short";
        }

        for (int i = 0; i < workLine.length(); i++) {
            char ch = workLine.charAt(i);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                return  "Please, use only latin alphabet for username";
            }
        }

        return VALIDATED_OK;
    }

    private String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Please, fill in the password field";
        }
        String workLine = password.trim();
        if (workLine.indexOf(' ') > 0) {
            return "Please, remove space from password";
        }
        if (workLine.length() < 6) {
            return "password is too short";
        }
        return VALIDATED_OK;
    }
}
