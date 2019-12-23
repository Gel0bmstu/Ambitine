package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.conroller.ExportPromiseController;
import com.example.networking.model.models.Promise;

import java.util.ArrayList;
import java.util.List;


public class ExportPromiseFragment extends Fragment {
    private ExportPromiseController exportPromiseController;
    private RecyclerView.Adapter promiseAdapter;
    private RecyclerView.LayoutManager promeseLayoutManager;
    View rootView;
    private List<Promise> gettedPromises = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (exportPromiseController == null) {
            exportPromiseController = new ExportPromiseController(this);
        }

        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_export_promise_feed, container, false);
        exportPromiseController.getFeedData();
        return rootView;
    }






    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            exportPromiseController.getFeedData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}