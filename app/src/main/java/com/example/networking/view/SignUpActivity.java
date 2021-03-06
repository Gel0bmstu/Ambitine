package com.example.networking.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.networking.R;

import com.example.networking.controller.SignUpController;
import com.example.networking.debugtools.AmbitinedToast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {
    SignUpController signUpController;
    SignUpActivity signUpActivity = this;

    Uri fileUri = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        if (signUpController == null) {
            signUpController = new SignUpController(this);
        }

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpController.onSignUpClick();
            }
        });

        ImageView uploadButton = signUpActivity.findViewById(R.id.upload_button);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(signUpActivity)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                CropImage.activity()
                                        .start(signUpActivity);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(signUpActivity);
                                    builder.setTitle("Permission Required")
                                            .setMessage("Permission to access your device storage is" +
                                                    " required to pick your profile image. Please " +
                                                    "go to settings to enable permission to access storage")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getPackageName(), null));
                                                    startActivityForResult(intent, 5);
                                                }
                                            })
                                            .setNegativeButton("Cancel", null)
                                            .show();
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .check();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                fileUri = result.getUri();
                ImageView profileAvatarView = signUpActivity.findViewById(R.id.profile_avatar);
                profileAvatarView.setImageURI(fileUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                System.out.println(error);
            }
        }
    }

    public String getUsername() {
        TextInputEditText usernameInput = findViewById(R.id.login_input);
        return Objects.requireNonNull(usernameInput.getText()).toString();
    }

    public String getToken() {
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

    public void printError(String error) {
        AmbitinedToast.getInstance().debugDarkColor(this, error);
    }
}
