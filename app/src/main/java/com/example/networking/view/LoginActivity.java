package com.example.networking.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;
import com.example.networking.conroller.LoginController;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (loginController == null) {
            loginController = new LoginController(this);
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginController.onLoginButtonClick();
            }
        });
    }

    public String getUsername() {
        TextInputEditText usernameInput = findViewById(R.id.login_input);
        return usernameInput.getText().toString();
    }

    public String getPassword() {
        TextInputEditText passwordInput = findViewById(R.id.password_input);
        return passwordInput.getText().toString();
    }

    public void SwitchActivityAfterLoginSuccess() {
        Intent HomeIntent = new Intent(this, HomeActivity.class);
        startActivity(HomeIntent);
    }
}
