package com.example.networking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.networking.R;
import com.example.networking.model.User;
import com.example.networking.model.network.ApiService;
import com.example.networking.model.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiService api = RetrofitClient.getApiService();

        Call<User> call = api.get();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    TextView textView = findViewById(R.id.textview);
                    textView.setText("WHAT");
                    Log.d("WHAT", "nice");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("WHAT", t.getMessage());
                Log.d("WHAT", "fuck");
            }
        });
    }
}
