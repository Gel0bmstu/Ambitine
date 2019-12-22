package com.example.networking.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.networking.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    final Fragment exportPromiseFragment  = new ExportPromiseFragment();
    final Fragment newPromiseFragment  = new PromiiseCreaterFragment();
    final Fragment profileFragment= new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = exportPromiseFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.bottom_nav_promises:
                    fm.beginTransaction().hide(active).show(exportPromiseFragment).commit();
                    active = exportPromiseFragment;
                    return true;

                case R.id.botton_nav_new_promise:
                    fm.beginTransaction().hide(active).show(newPromiseFragment).commit();
                    active = newPromiseFragment;
                    return true;

                case R.id.bottom_nav_profile:
                    fm.beginTransaction().hide(active).show(profileFragment).commit();
                    active = profileFragment;
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        fm.beginTransaction().add(R.id.content_fragment, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.content_fragment, profileFragment, "profile_fragment").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.content_fragment, newPromiseFragment, "new_promise_fragment").hide(newPromiseFragment).commit();
        fm.beginTransaction().add(R.id.content_fragment, exportPromiseFragment, "feed_fragment").commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_fragment, feedFragment).commit();


//        ImageDownloader.LoadImage(getApplicationContext(), 1);
//        imageView.setImageBitmap(LocalStorage.GetImage(getApplicationContext(), 1));
    }
}