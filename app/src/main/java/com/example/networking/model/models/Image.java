package com.example.networking.model.models;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Image {
    public void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }
}
