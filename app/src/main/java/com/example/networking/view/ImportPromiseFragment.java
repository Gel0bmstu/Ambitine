package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.networking.R;
import com.example.networking.conroller.ImportPromiseController;

public class ImportPromiseFragment extends Fragment {
    private ImportPromiseController importPromiseController;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (importPromiseController == null) {
            importPromiseController = new ImportPromiseController(this);
        }

        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_import_promise_feed, container, false);
        importPromiseController.getFeedData();
        return rootView;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            importPromiseController.getFeedData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}