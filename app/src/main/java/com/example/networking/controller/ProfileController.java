package com.example.networking.controller;

import com.example.networking.model.models.Profile;
import com.example.networking.model.network.Retrofit.ProfileService;
import com.example.networking.view.ProfileFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.networking.model.network.Retrofit.Interceptors.AddCookiesInterceptor;
import com.example.networking.model.network.Retrofit.Interceptors.ReceivedCookiesInterceptor;

public class ProfileController {
    ProfileFragment profileFragment;

    public ProfileController(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public void getProfileData() {
        Call<Profile> call = service.getAllFeedItems();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                System.out.println("SHKET POMOYNOY GADZY111:" + String.valueOf(response.code()));
                if (response.code() == 200) {
                    assert response.body() != null;
                    System.out.println("WATA SHAKA LAKA");
                    Profile profile = response.body();
                    profileFragment.setProfileData(profile);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
            }
        });
    }

    Gson gson = new GsonBuilder().create();
    OkHttpClient client = new OkHttpClient.Builder().
            addInterceptor(new AddCookiesInterceptor()).
            addInterceptor(new ReceivedCookiesInterceptor()).
            build();

    ProfileService service = new Retrofit.Builder()
//            .baseUrl("http://www.mocky.io/")
//            .baseUrl("http://192.168.100.32:9090")
            .baseUrl("http://35.228.98.103:9090/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ProfileService.class);
}
