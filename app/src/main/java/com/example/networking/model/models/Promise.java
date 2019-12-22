package com.example.networking.model.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Promise {
    private Integer id;
    private String author_username;
    private String receiver_username;
    private String receiver_img_url;
    private String author_img_url;
    private String promise_description;
    private Long pastdue;
    private Float deposit;
    private Integer accepted;

    public String getUsername() {
        return this.receiver_username;
    }

    public String getAuthor_username() {
        return this.author_username;
    }

    public String getImg_url() {
        return receiver_img_url;
    }

    public Float getDeposit() {
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


    public Integer getAccepted() {
        return accepted;
    }


    public void setAccepted(Integer accepted) {
        this.accepted = accepted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
