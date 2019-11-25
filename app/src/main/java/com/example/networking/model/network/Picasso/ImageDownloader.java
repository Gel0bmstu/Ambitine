package com.example.networking.model.network.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDownloader {
    public static void LoadImage(Context context, int userId, ImageView imageView) {
        Picasso.get().load("https://cdn5.vectorstock.com/i/1000x1000/46/59/programmer-icon-in-flat-style-isolated-on-white-vector-14324659.jpg")
                .into(imageView);
    }
}

