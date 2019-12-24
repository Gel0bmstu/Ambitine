package com.example.networking.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.networking.R;
import com.example.networking.view.feeds.fragments.ExportPromiseFragment;
import com.example.networking.view.feeds.fragments.ImportPromiseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity {

    final Fragment exportPromiseFragment  = new ExportPromiseFragment();
    final Fragment importPromiseFragment  = new ImportPromiseFragment();
    final Fragment newPromiseFragment  = new PromiseCreaterFragment();
    final Fragment profileFragment= new ProfileFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = exportPromiseFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.bottom_nav_export_promises:
                    fm.beginTransaction().hide(active).show(exportPromiseFragment).commit();
                    active = exportPromiseFragment;
                    return true;

                case R.id.bottom_nav_import_promises:
                    fm.beginTransaction().hide(active).show(importPromiseFragment).commit();
                    active = importPromiseFragment;
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

    public void clickFeedButton() {
        BottomNavigationItemView feedButton = findViewById(R.id.bottom_nav_export_promises);
        feedButton.performClick();
        feedButton.setPressed(true);
        feedButton.invalidate();
        feedButton.setPressed(false);
        feedButton.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fm.beginTransaction().add(R.id.content_fragment, profileFragment, "profile_fragment").hide(profileFragment).commit();
        fm.beginTransaction().add(R.id.content_fragment, newPromiseFragment, "new_promise_fragment").hide(newPromiseFragment).commit();
        fm.beginTransaction().add(R.id.content_fragment, importPromiseFragment, "import_promise_feed").hide(importPromiseFragment).commit();
        fm.beginTransaction().add(R.id.content_fragment, exportPromiseFragment, "export_promise_feed").commit();
    }
}