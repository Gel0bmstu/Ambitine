package com.example.networking.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.conroller.ProfileController;
import com.example.networking.model.models.Profile;
import com.example.networking.model.network.Retrofit.Api;
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1;
    private String UPLOAD_URL = Api.BASE_URL + "api/img_upload";

    private Uri filePath;

    View rootView;
    ProfileController profileController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("SHKET POMOYNOY GADZY");

        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        if (profileController == null) {
            profileController = new ProfileController(this);
        }
        profileController.getProfileData();

        ImageView uploadPhotoBtn = rootView.findViewById(R.id.upload_button);
        uploadPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("govnyaka");
                showFileChooser();
                uploadMultipart();
            }
        });
        return rootView;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void uploadMultipart() {
        //getting the actual path of the image
        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
                new MultipartUploadRequest(getActivity(), UPLOAD_URL)
                    .addFileToUpload(path, "image") //Adding file
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(2)
                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
            Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                ImageView avatar = rootView.findViewById(R.id.profile_avatar);
                avatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setProfileData(Profile profile) {
        ImageView profileAvatarView = rootView.findViewById(R.id.profile_avatar);
        TextView profileUsernameView = rootView.findViewById(R.id.profile_username);
        TextView profileWalletView = rootView.findViewById(R.id.profile_wallet);
        TextView profileBalanceView = rootView.findViewById(R.id.profile_balance);
        TextView profilePromisesView = rootView.findViewById(R.id.profile_promises);

        Picasso.get().load(profile.getImg_url())
                .resize(500, 700)
                .noFade()
                .into(profileAvatarView);

        profileUsernameView.setText(profile.getUsername());

        String walletText = "Wallet: " + profile.getWallet();
        profileWalletView.setText(walletText);

        String balanceText = "Balance: " + profile.getBalance();
        profileBalanceView.setText(balanceText);

        String promisesText = "ACC / DEC / PROC: " +
                profile.getAccepted_count() + "/" +
                profile.getDeclined_count() + "/" +
                profile.getProcessing_count();
        profilePromisesView.setText(promisesText);
    }
}