package com.example.networking.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.util.Log;

import com.example.networking.model.UserRepository;

import org.jetbrains.annotations.NotNull;

public class Utils {
    public static void InternetCheckListener (Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        connectivityManager.registerNetworkCallback(
                new NetworkRequest.Builder().build(),
                new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(@NotNull Network network) {
                        super.onAvailable(network);
                        UserRepository.InternetIsAvailable = true;
                        Log.d("hello", "ava");
                    }

                    @Override
                    public void onLost(@NotNull Network network) {
                        super.onLost(network);
                        UserRepository.InternetIsAvailable = false;
                        Log.d("hello", "lost");
                    }

                });
    }
}
