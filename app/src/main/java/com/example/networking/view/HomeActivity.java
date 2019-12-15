package com.example.networking.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.networking.R;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Fragment feedFragment  = new FeedFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, feedFragment).commit();


//        ImageDownloader.LoadImage(getApplicationContext(), 1);
//        imageView.setImageBitmap(LocalStorage.GetImage(getApplicationContext(), 1));
    }
}
