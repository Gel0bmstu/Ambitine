package com.example.networking.model.models;

public class Profile {
    private String username;
    private String img_url;
    private Integer accepted_count;
    private Integer declined_count;
    private Integer processing_count;
    private Integer balance;
    private String wallet;

    public String getUsername() {
        return username;
    }

    public String getImg_url() {
        return img_url;
    }

    public Integer getAccepted_count() {
        return accepted_count;
    }

    public Integer getDeclined_count() {
        return declined_count;
    }

    public Integer getProcessing_count() {
        return  processing_count;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getWallet() {
        return wallet;
    }
}
