package com.example.networking.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Picasso.ImageDownloader;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = findViewById(R.id.imageView);
        ImageDownloader.loadImage(imageView, Api.BASE_URL + "api/getDefault");
    }
}
