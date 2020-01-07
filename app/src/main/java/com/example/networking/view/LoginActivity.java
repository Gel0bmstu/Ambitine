package com.example.networking.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;
import com.example.networking.controller.LoginController;
import com.example.networking.debugtools.AmbitinedToast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

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

        TextView signUpButton = findViewById(R.id.signup_link);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToSignUp();
            }
        });
    }

    public void SwitchToSignUp() {
        Intent SignUpIntent = new Intent(this, SignUpActivity.class);
        startActivity(SignUpIntent);
    }

    public String getUsername() {
        TextInputEditText usernameInput = findViewById(R.id.login_input);
        return Objects.requireNonNull(usernameInput.getText()).toString();
    }

    public String getToken() {
        // ToDo: Remove deprecated version
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("TOKEN OUT");
        System.out.println(token);
        return token;
    }

    public String getPassword() {
        TextInputEditText passwordInput = findViewById(R.id.password_input);
        return Objects.requireNonNull(passwordInput.getText()).toString();
    }

    public void SwitchActivityAfterLoginSuccess() {
        Intent HomeIntent = new Intent(this, HomeActivity.class);
        startActivity(HomeIntent);
    }

    public void printError(String error) {
        AmbitinedToast.getInstance().debugDarkColor(this, error);
    }
}
