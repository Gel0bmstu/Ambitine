package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.Promise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedService {
    @GET("v2/5dfce103310000efc8d2c221/")
    Call<List<Promise>> getAllFeedItems();
}