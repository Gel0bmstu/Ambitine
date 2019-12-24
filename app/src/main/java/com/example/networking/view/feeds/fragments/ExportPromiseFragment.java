package com.example.networking.view.feeds.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.networking.R;
import com.example.networking.controller.FeedPromiseController;


public class ExportPromiseFragment extends Fragment {
    private FeedPromiseController exportPromiseController;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (exportPromiseController == null) {
            exportPromiseController = new FeedPromiseController(this);
        }

        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_export_promise_feed, container, false);
        exportPromiseController.setExportFeedData();
        return rootView;
    }






    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            exportPromiseController.setExportFeedData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}