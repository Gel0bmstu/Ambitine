package com.example.networking.model.network.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageDownloader {
    public static void LoadImage(Context context, int userId, final ImageView imageView) {
        String url = "https://huntpng.com/images250/avatar-png-5.png";
        Picasso.get().load(url)
                .into(imageView);
    }
}

