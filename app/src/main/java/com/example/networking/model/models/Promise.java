package com.example.networking.model.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Promise {
    private String author_username;
    private String reciever_username;
    private String img_url;
    private String promise_description;
    private Long pastdue;
    private Integer deposit;
    private Boolean accepted;

    public String getUsername() {
        return this.reciever_username;
    }

    public String getImg_url() {
        return img_url;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public String getPromiseDescription() {
        return promise_description;
    }

    public String getPastDue() {
        Date date = new java.util.Date(pastdue*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+3"));
        return sdf.format(date);
    }


}
