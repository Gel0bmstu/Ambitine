package com.example.networking.model.network.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewPromiseResponce {
//    {
//        "reciever_username": "gel0",
//            "promise_description": "NE HRAPI SUKA",
//            "pastdue": 12,
//            "deposit": 500,
//            "accepted": 1,
//    }

    @SerializedName("reciever_username")
    @Expose
    private String reciever_username;

    @SerializedName("promise_description")
    @Expose
    private String promise_description;

    @SerializedName("pastdue")
    @Expose
    private Long pastdue;

    @SerializedName("deposit")
    @Expose
    private Integer deposit;

    public NewPromiseResponce(String reciever_username, String promise_description, Long pastdue, Integer deposit) {
        setReciever_username(reciever_username);
        setPromise_description(promise_description);
        setDeposit(deposit);
        setPastdue(pastdue);
    }

    public Long getPastdue() {
        return pastdue;
    }

    public void setPastdue(Long pastdue) {
        this.pastdue = pastdue;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getPromise_description() {
        return promise_description;
    }

    public void setPromise_description(String promise_description) {
        this.promise_description = promise_description;
    }

    public String getReciever_username() {
        return reciever_username;
    }

    public void setReciever_username(String reciever_username) {
        this.reciever_username = reciever_username;
    }
}
