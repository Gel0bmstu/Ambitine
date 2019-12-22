package com.example.networking.conroller;

import android.util.Log;

import com.example.networking.R;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.view.LoginActivity;
import com.example.networking.view.PromiiseCreaterFragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPromiseController {
    private static String logTag;

    private PromiiseCreaterFragment newPromiseFragment;

    public NewPromiseController(PromiiseCreaterFragment promiseFragment) {
        this.newPromiseFragment = promiseFragment;
    }


    public void onNewPromiseButtonClick() {
        String username = newPromiseFragment.getRecieverUsername();
//        LoginResponse loginResponse = new NewPromiseController(username, password, token);
//        login(loginResponse);
    }

//    private void login(final LoginResponse loginResponse) {
//        ApiService apiService = Api.getApiService();
//        Call<ResponseBody> call = apiService.loginRequest(loginResponse);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.code() == 201) {
//                    loginActivity.SwitchActivityAfterLoginSuccess();
//                } else {
//                    Log.d(logTag, "smth get wrong!");
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d(logTag, t.getMessage());
//            }
//        });
//    }

}