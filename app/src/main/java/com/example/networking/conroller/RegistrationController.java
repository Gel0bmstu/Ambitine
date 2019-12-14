package com.example.networking.conroller;

import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.RegistrationResponse;
import com.example.networking.view.RegistrationActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationController {
    private RegistrationActivity registrationActivity;

    public RegistrationController(RegistrationActivity registrationActivity) {
        this.registrationActivity = registrationActivity;
    }

    public RegistrationController() {
    }

    public void onSignUpClick() {
//        String username = RegistrationActivity.getUsername();
//        String passowrd = RegistrationActivity.getPassword();
        String username = "test";
        String password = "test";

        RegistrationResponse registrationResponse = new RegistrationResponse(username, password);
        signUp(registrationResponse);
    }

    private void signUp(final RegistrationResponse registrationResponse) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.signupRequest(registrationResponse);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 201) {
//                    String authToken = response.headers().get("Set-Cookie");
//
//                    UserRepository.setToken(authToken);
//
//                    loginActivity.SwitchActivityAfterLoginSuccess();
//
//                    Log.d(logTag, UserRepository.getToken());
//                    Log.d(logTag, authToken);
                } else {
//                    Log.d(logTag, "smth get wrong!");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(logTag, t.getMessage());
            }
        });
    }
}
