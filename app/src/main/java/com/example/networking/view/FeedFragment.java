package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.conroller.FeedController;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.FeedService;
import com.example.networking.model.network.Retrofit.Response.FeedPromiseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedFragment extends Fragment {
    private FeedController feedController;
    private RecyclerView.Adapter promiseAdapter;
    private RecyclerView.LayoutManager promeseLayoutManager;
    View rootView;
    private List<Promise> gettedPromises = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (feedController == null) {
            feedController = new FeedController(this);
        }

        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_promise_feed, container, false);
        getFeedData();
        return rootView;
//        return super.onCreateView(inflater, containeer, savedInstanceState);
    }


    Gson gson = new GsonBuilder().create();

    // use retrofit to create an instance of BookService
    FeedService service = new Retrofit.Builder()
//            .baseUrl("http://www.mocky.io/")
            .baseUrl("http://35.228.98.103:9090/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FeedService.class);

    public void getFeedData() {
        ApiService apiService = Api.getApiService();
        Call<List<Promise>> call = service.getAllFeedItems();
        System.out.println("WE INB");
        call.enqueue(new Callback<List<Promise>>() {
            @Override
            public void onResponse(Call<List<Promise>> call, Response<List<Promise>> response) {
                System.out.println("WATA SHAKA LAKA");
                if (response.code() == 200) {
                    assert response.body() != null;
                    System.out.println("WATA SHAKA LAKA");
                    // Tmp method to get data
                    RecyclerView recyclerView = rootView.findViewById(R.id.promise_feed);


                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


                    // feedController.setFeedData();
//                    feedController.setFeedData();
                    String[] myStringArray;
                    List<Promise> promises = response.body();
                    PromiseAdapter mAdapter = new PromiseAdapter(promises);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } else {
                    System.out.println("Another handle way");
                }
            }

            @Override
            public void onFailure(Call<List<Promise>> call, Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
            }
        });
    }

    @Override
    public void onResume() {

        super.onResume();
    }
}