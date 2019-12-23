package com.example.networking.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.example.networking.R;
import com.example.networking.conroller.ProfileController;
import com.example.networking.model.UserRepository;
import com.example.networking.model.models.Profile;
import com.example.networking.model.network.Retrofit.Api;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.networking.model.network.Retrofit.Api.BASE_URL;

public class ProfileFragment extends Fragment {
    private int PICK_IMAGE_REQUEST = 1;
    public static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    private String UPLOAD_URL = BASE_URL + "api/img_upload";

    private Uri fileUri;
    ProfileFragment profileFragment = this;
    ProgressDialog progressDialog;

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
                Dexter.withActivity(getActivity())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                CropImage.activity()
                                        .start(getContext(), profileFragment);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                if (response.isPermanentlyDenied()) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setTitle("Permission Required")
                                            .setMessage("Permission to access your device storage is" +
                                                    " required to pick your profile image. Please " +
                                                    "go to settings to enable permission to access storage")
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent();
                                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                                    intent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
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
//                System.out.println("govnyaka");
//                showFileChooser();
            }
        });


        PieChart pieChart = (PieChart) rootView.findViewById(R.id.chart1);

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(2, "Accepted"));
        entries.add(new PieEntry(1, "Declined"));
        entries.add(new PieEntry(0, "Processed"));


        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        PieData data = new PieData(dataset);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS); //
//        pieChart.setDescription("Description");
        pieChart.setData(data);

        pieChart.animateY(3000);

//        anyChartView.draw();

        return rootView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            profileController.getProfileData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                fileUri = result.getUri();
                ImageView profileAvatarView = rootView.findViewById(R.id.profile_avatar);
                profileAvatarView.setImageURI(fileUri);

                File imageFile = new File(fileUri.getPath());
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Uploading image. Please, Wait");
                progressDialog.setCancelable(false);
                progressDialog.setMax(100);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                AndroidNetworking.upload(BASE_URL + "api/img_upload")
                        .addMultipartFile("avatar", imageFile)
                        .addHeaders("Cookie", UserRepository.getToken())
                        .setPriority(Priority.HIGH)
                        .build()
                        .setUploadProgressListener(new UploadProgressListener() {
                            @Override
                            public void onProgress(long bytesUploaded, long totalBytes) {
                                float progress = (float) bytesUploaded / totalBytes * 100;
                                progressDialog.setProgress((int)progress);
                            }
                        })
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                Log.d("waakaa code", response);
                            }

                            @Override
                            public void onError(ANError anError) {
                                progressDialog.dismiss();
                                Log.d("wakaaa shaakaa", anError.getErrorDetail());
                            }
                        });
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                System.out.println(error);
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