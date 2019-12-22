package com.example.networking.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.conroller.ImportPromiseController;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.ExportPromiseService;
import com.example.networking.model.network.Retrofit.Interceptors.AddCookiesInterceptor;
import com.example.networking.model.network.Retrofit.Interceptors.ReceivedCookiesInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.networking.model.network.Retrofit.Api.BASE_URL;

public class ImportPromiseFragment extends Fragment {
    private ImportPromiseController importPromiseController;
    private RecyclerView.Adapter promiseAdapter;
    private RecyclerView.LayoutManager promeseLayoutManager;
    View rootView;
    private List<Promise> gettedPromises = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (importPromiseController == null) {
            importPromiseController = new ImportPromiseController(this);
        }

        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_promise_feed, container, false);
        getFeedData();
//        rootView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//            @Override
//            public void onScrollChanged() {
//                if (rootView != null) {
//                    if (rootView.getScrollY() == 0) {
//                        Toast.makeText(getContext(), "top", Toast.LENGTH_SHORT).show();
//                    } else {
//
//                    }
//                }
//            }
//        });
        return rootView;
//        return super.onCreateView(inflater, containeer, savedInstanceState);
    }


    Gson gson = new GsonBuilder().create();
    OkHttpClient client = new OkHttpClient.Builder().
            addInterceptor(new AddCookiesInterceptor()).
            addInterceptor(new ReceivedCookiesInterceptor()).
            build();

    // use retrofit to create an instance of BookService
    ExportPromiseService service = new Retrofit.Builder()
//            .baseUrl("http://www.mocky.io/")
//            .baseUrl("http://192.168.100.32:9090")
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(ExportPromiseService.class);

    public void getFeedData() {
        ApiService apiService = Api.getApiService();
        Call<List<Promise>> call = service.getAllImportPromises();
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

                } else if (response.code() == 404) {
                    System.out.println("4040404400400440");
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            getFeedData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}