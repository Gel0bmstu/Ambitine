package com.example.networking.conroller;

import android.util.Log;

import com.example.networking.R;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.model.network.Retrofit.Response.NewPromiseResponce;
import com.example.networking.view.LoginActivity;
import com.example.networking.view.PromiiseCreaterFragment;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPromiseController {

    private PromiiseCreaterFragment newPromiseFragment;

    public NewPromiseController(PromiiseCreaterFragment promiseFragment) {
        this.newPromiseFragment = promiseFragment;
    }


    public void onNewPromiseButtonClick() {
        String username = newPromiseFragment.getRecieverUsername();
        Integer deposit = newPromiseFragment.getDeposit();
        String description = newPromiseFragment.getPromiseDescription();
        Long pastdue = newPromiseFragment.getPastDue();
        NewPromiseResponce newPromiseReponce = new NewPromiseResponce(username, description, pastdue, deposit);
        sendNewPromise(newPromiseReponce);
    }

    private void sendNewPromise(final NewPromiseResponce newPromiseResponce) {
        ApiService apiService = Api.getApiService();
        Call<ResponseBody> call = apiService.sendNewPromise(newPromiseResponce);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.code() == 201) {
                    System.out.println("New promise send");
                } else {
                    System.out.println("Another code");
                    System.out.println(response.code());
                }
            }
            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                System.out.println("Send new promise failure");
                System.out.println(t.toString());
            }
        });
    }

}