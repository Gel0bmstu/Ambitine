package com.example.networking.model.network.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AcceptResponse {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("accepted")
    @Expose
    private Integer accepted;


    public AcceptResponse(Integer id, Integer accepted) {
        this.id = id;
        this.accepted = accepted;
    }
}
