package com.example.networking.view;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

import net.gotev.uploadservice.data.UploadNotificationConfig;
import net.gotev.uploadservice.protocols.multipart.MultipartUploadRequest;

import java.util.UUID;

public class ProfileFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1;

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
        //getting name for the image
//        String name = editText.getText().toString().trim();

        //getting the actual path of the image
//        String path = getPath(filePath);

        //Uploading code
        try {
            String uploadId = UUID.randomUUID().toString();

            //Creating a multi part request
//                new MultipartUploadRequest(this, uploadId, Constants.UPLOAD_URL)
//                    .addFileToUpload(path, "image") //Adding file
//                    .addParameter("name", name) //Adding text parameter to the request
//                    .setNotificationConfig(new UploadNotificationConfig())
//                    .setMaxRetries(2)
//                    .startUpload(); //Starting the upload

        } catch (Exception exc) {
//            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
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