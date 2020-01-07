package com.example.networking.model.models;

public class Profile {
    private Float accepted_amount;
    private Float declined_amount;
    private Float processing_amount;
    private String username;
    private String img_url;
    private Integer accepted_count;
    private Integer declined_count;
    private Integer processing_count;
    private Float balance;
    private Float debt;
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

    public Float getBalance() {
        return balance;
    }

    public String getWallet() {
        return wallet;
    }

    public Float getDebt() {
        return debt;
    }
}
