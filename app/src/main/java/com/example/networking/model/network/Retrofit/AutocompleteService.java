package com.example.networking.model.network.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutocompleteService {
    @GET("api/users_autocomplete/")
    Call<List<String>> getUsersAutocomplete();
}
