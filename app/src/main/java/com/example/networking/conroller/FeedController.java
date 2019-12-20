package com.example.networking.conroller;

import android.util.Log;
import android.widget.Toast;

import com.example.networking.R;
import com.example.networking.model.UserRepository;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.FeedPromiseResponse;
import com.example.networking.model.network.Retrofit.Response.LoginResponse;
import com.example.networking.view.FeedFragment;
import com.example.networking.view.LoginActivity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedController {
    private FeedFragment feedFragment;

    public FeedController(FeedFragment feedFragment) {
        this.feedFragment = feedFragment;
    }
    // ToDo: add offset to args
}
