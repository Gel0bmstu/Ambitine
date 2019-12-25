package com.example.networking.controller;

import android.widget.Toast;

import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.NewPromiseResponce;
import com.example.networking.view.PromiseCreaterFragment;

import org.jetbrains.annotations.NotNull;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPromiseController {

    private PromiseCreaterFragment newPromiseFragment;

    public NewPromiseController(PromiseCreaterFragment promiseFragment) {
        this.newPromiseFragment = promiseFragment;
    }

    public Long getNowTimestamp() {
        Date date= new Date();
        return date.getTime();
    }


    public void onNewPromiseButtonClick() {
        String username = newPromiseFragment.getRecieverUsername();
        Integer deposit = newPromiseFragment.getDeposit();
        String description = newPromiseFragment.getPromiseDescription();
        Long pastdue = newPromiseFragment.getPastDue();
        // Date checker

        int compared = pastdue.compareTo(getNowTimestamp());
        if (compared > 0  || compared == 0) {
            Toast.makeText(Objects.requireNonNull(newPromiseFragment.getActivity())
                    .getApplicationContext(),"Available only if you are timetraveller!",Toast.LENGTH_SHORT).show();
        } else {
            NewPromiseResponce newPromiseReponce = new NewPromiseResponce(username, description, pastdue, deposit);
            sendNewPromise(newPromiseReponce);
        }

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
                Toast.makeText(Objects.requireNonNull(newPromiseFragment.getActivity()).getApplicationContext(),"New promise create failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

}