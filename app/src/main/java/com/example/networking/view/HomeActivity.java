package com.example.networking.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.model.network.Picasso.ImageDownloader;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView imageView = findViewById(R.id.imageView);
        final FeedFragment feedFragment  = new FeedFragment();
        feedFragment.TestFeedData();
        ImageDownloader.LoadImage(getApplicationContext(), 1, imageView);

//        ImageDownloader.LoadImage(getApplicationContext(), 1);
//        imageView.setImageBitmap(LocalStorage.GetImage(getApplicationContext(), 1));
    }
}
