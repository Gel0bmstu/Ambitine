package com.example.networking.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;

import com.example.networking.conroller.SignUpController;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    SignUpController signUpController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (signUpController == null) {
            signUpController = new SignUpController(this);
        }

        Button loginButton = findViewById(R.id.sign_up_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpController.onSignUpClick();
            }
        });
    }

    public String getUsername() {
        TextInputEditText usernameInput = findViewById(R.id.login_input);
        return Objects.requireNonNull(usernameInput.getText()).toString();
    }

    public String getToken(){
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("TOKEN OUT");
        System.out.println(token);
        return token;
    }

    public String getPassword() {
        TextInputEditText passwordInput = findViewById(R.id.password_input);
        return Objects.requireNonNull(passwordInput.getText()).toString();
    }

    public void SwitchActivityAfterSignUpSuccess() {
        Intent HomeIntent = new Intent(this, HomeActivity.class);
        startActivity(HomeIntent);
    }
}
