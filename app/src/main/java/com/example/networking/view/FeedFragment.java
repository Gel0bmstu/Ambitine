package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.conroller.FeedController;

public class FeedFragment extends Fragment {
    private FeedController feedController;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        feedController = new FeedController(this);
        feedController.setFeedData();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    public void TestFeedData() {
        feedController = new FeedController(this);
        feedController.setFeedData();
    }
}