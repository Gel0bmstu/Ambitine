package com.example.networking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.networking.R;
import com.example.networking.model.database.UserDB;

import io.realm.Realm;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Realm.init(getApplicationContext());

        UserDB.deleteToken();

        if (!UserDB.isAuthorized()) {
            Intent LoginIntent = new Intent(this, LoginActivity.class);
            startActivity(LoginIntent);
        } else {
            Intent HomeIntent = new Intent(this, HomeActivity.class);
            startActivity(HomeIntent);
        }
    }
}