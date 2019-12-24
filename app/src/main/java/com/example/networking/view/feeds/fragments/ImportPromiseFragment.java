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

public class ImportPromiseFragment extends Fragment {
    private FeedPromiseController exportPromiseController;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (exportPromiseController == null) {
            exportPromiseController = new FeedPromiseController(this);
        }

        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_import_promise_feed, container, false);
        exportPromiseController.setImportFeedData();
        return rootView;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            exportPromiseController.setImportFeedData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}