package com.example.networking.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;
import com.example.networking.conroller.LoginController;

public class LoginActivity extends AppCompatActivity {
    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (loginController == null) {
            loginController = new LoginController(this);
        }
    }

    private class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.string.loginButton:
                    loginController.onLoginButtonClick();
            }
        }
    }

    public String getUsername() {
        return "hello";
    }

    public String getPassword() {
        return "myFriend";
    }

}
