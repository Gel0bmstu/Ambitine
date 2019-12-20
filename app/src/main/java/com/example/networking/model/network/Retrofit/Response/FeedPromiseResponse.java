package com.example.networking.model.network.Retrofit.Response;

import com.example.networking.model.models.Promise;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedPromiseResponse {

    @SerializedName("nickname")
    @Expose
    private String nickname;

    @SerializedName("img_url")
    @Expose
    private String imgUrl;

    @SerializedName("promise_description")
    @Expose
    private String promiseDescription;

    @SerializedName("pastdue")
    @Expose
    private Long pastdue;

    @SerializedName("deposit")
    @Expose
    private Integer deposit;


    public FeedPromiseResponse(String nickname, String imgUrl, String promiseDescription, Long pastdue, Integer deposit) {
       this.nickname = nickname;
       this.imgUrl = imgUrl;
       this.promiseDescription = promiseDescription;
       this.pastdue = pastdue;
       this.deposit = deposit;
    }

    public String getNickname() {
        return this.nickname;
    }
}
