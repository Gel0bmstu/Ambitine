package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.conroller.ProfileController;
import com.example.networking.model.models.Profile;
import com.squareup.picasso.Picasso;

public class ProfileFragment extends Fragment {
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
        return rootView;
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