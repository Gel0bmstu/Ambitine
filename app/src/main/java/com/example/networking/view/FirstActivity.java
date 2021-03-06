package com.example.networking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.networking.model.database.UserDB;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FirstActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

//        UserDB.deleteToken();

        if (!UserDB.isAuthorized()) {
            Intent LoginIntent = new Intent(this, LoginActivity.class);
            startActivity(LoginIntent);
        } else {
            Intent HomeIntent = new Intent(this, HomeActivity.class);
            if (getIntent().getExtras() != null) {
                HomeIntent.putExtras(getIntent().getExtras());
            }
            startActivity(HomeIntent);
        }
    }
}