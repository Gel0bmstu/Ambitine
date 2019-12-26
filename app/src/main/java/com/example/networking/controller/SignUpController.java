package com.example.networking.controller;

import android.util.Log;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;
import com.example.networking.view.SignUpActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpController {
    private SignUpActivity signUpActivity;

    public SignUpController(SignUpActivity signUpActivity) {
        this.signUpActivity = signUpActivity;
    }

    public void onSignUpClick() {
        String username = signUpActivity.getUsername();
        String password = signUpActivity.getPassword();
        String token = signUpActivity.getToken();

        RegistrationResponse registrationResponse = new RegistrationResponse(username, password, token);
        signUp(registrationResponse);
    }



    private void signUp(final RegistrationResponse registrationResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.signUpRequest(registrationResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int responseCode = response.code();
                if (responseCode == 201) {
                    signUpActivity.SwitchActivityAfterSignUpSuccess();
                } else if (responseCode == 409) {
                    String userConflictMessage = signUpActivity.getResources().getString(R.string.profile_data_error);
                    AmbitinedToast.getInstance().debug(signUpActivity, userConflictMessage);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(logTag, t.getMessage());
            }
        });
    }
}
