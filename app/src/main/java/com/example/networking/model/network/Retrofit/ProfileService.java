package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.Profile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfileService {
    @GET("api/user_info/")
    Call<Profile> getAllFeedItems();
}
