package com.example.networking.conroller;

import android.util.Log;

import com.example.networking.model.UserRepository;
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

        RegistrationResponse registrationResponse = new RegistrationResponse(username, password);
        signUp(registrationResponse);
    }

    private void signUp(final RegistrationResponse registrationResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.signUpRequest(registrationResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
                    signUpActivity.SwitchActivityAfterSignUpSuccess();
                } else {
                    Log.d("hellomelloy", "smth get wrong!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(logTag, t.getMessage());
            }
        });
    }
}
