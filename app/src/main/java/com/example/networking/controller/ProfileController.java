package com.example.networking.controller;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.models.Profile;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.view.ProfileFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import org.jetbrains.annotations.NotNull;

public class ProfileController {
    private ProfileFragment profileFragment;

    public ProfileController(ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
    }

    public void getProfileData() {
        ApiService service = Api.getApiService();
        Call<Profile> call = service.getAllFeedItems();
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NotNull Call<Profile> call, @NotNull Response<Profile> response) {
                if (response.code() == 200) {
                    Profile profile = response.body();
                    assert profile != null;
                    profileFragment.setProfileData(profile);
                }
                profileFragment.setRefreshingStatus(false);
            }

            @Override
            public void onFailure(@NotNull Call<Profile> call, @NotNull Throwable t) {
                String recievingError = profileFragment.getResources().getString(R.string.profile_data_error);
                AmbitinedToast.getInstance().debugAboveTheKeyboard(profileFragment.getActivity(), recievingError);
                profileFragment.setRefreshingStatus(false);
            }

        });
    }
}
