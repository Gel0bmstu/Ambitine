package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.networking.R;
import com.example.networking.conroller.FeedController;

public class PromiiseCreaterFragment extends Fragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.promise_creater, container, false);
        return rootView;
//        return super.onCreateView(inflater, containeer, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
