package com.example.networking.model.network.Retrofit;

import com.example.networking.model.models.Promise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedService {
//    http://35.228.98.103:9090/api/get_promises
    @GET("api/get_promises/")
    Call<List<Promise>> getAllFeedItems();
}