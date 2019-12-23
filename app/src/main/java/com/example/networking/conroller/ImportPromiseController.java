package com.example.networking.conroller;

import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.ExportPromiseService;
import com.example.networking.model.network.Retrofit.Interceptors.AddCookiesInterceptor;
import com.example.networking.model.network.Retrofit.Interceptors.ReceivedCookiesInterceptor;
import com.example.networking.view.ImportPromiseFragment;
import com.example.networking.view.PromiseImportAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.networking.model.network.Retrofit.Api.BASE_URL;

public class ImportPromiseController {
    private ImportPromiseFragment importPromiseFragment;

    public ImportPromiseController(ImportPromiseFragment inImportPromiseFragment) {
        this.importPromiseFragment = inImportPromiseFragment;
    }

    private Gson gson = new GsonBuilder().create();
    private OkHttpClient client = new OkHttpClient.Builder().
            addInterceptor(new AddCookiesInterceptor()).
            addInterceptor(new ReceivedCookiesInterceptor()).
            build();

    // use retrofit to create an instance of BookService
    private ExportPromiseService service = new Retrofit.Builder()
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
            public void onResponse(@NotNull Call<List<Promise>> call, @NotNull Response<List<Promise>> response) {
                System.out.println("WATA SHAKA LAKA");
                if (response.code() == 200) {
                    assert response.body() != null;
                    System.out.println("WATA SHAKA LAKA");
                    RelativeLayout feedLayout = Objects.requireNonNull(importPromiseFragment.getView()).findViewById(R.id.import_promise_layout);
                    TextView promisesNotFound = importPromiseFragment.getView().findViewById(R.id.not_promises);
                    if (promisesNotFound != null) {
                        feedLayout.removeView(promisesNotFound);
                    }
                    // Tmp method to get data
                    RecyclerView recyclerView = importPromiseFragment.getView().findViewById(R.id.import_promise_feed);


                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(importPromiseFragment.getActivity()));


                    // feedController.setFeedData();
//                    feedController.setFeedData();
                    String[] myStringArray;
                    List<Promise> promises = response.body();
                    PromiseImportAdapter mAdapter = new PromiseImportAdapter(promises);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } else if (response.code() == 404) {
                    TextView promisesNotFound = new TextView(Objects.requireNonNull(importPromiseFragment.getView()).getContext());
                    promisesNotFound.setText(R.string.no_promises);
                    promisesNotFound.setId(R.id.not_promises);
                    // Dont work (
                    promisesNotFound.setGravity(Gravity.CENTER_HORIZONTAL);
                    promisesNotFound.setTextSize(25);
                    promisesNotFound.setTextColor(importPromiseFragment.getView().getResources().getColor(R.color.ambitine_primary_color));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0,350,0,0);
                    promisesNotFound.setLayoutParams(params);
                    RelativeLayout feedLayout = importPromiseFragment.getView().findViewById(R.id.import_promise_layout);
                    // ToDo: Maybe add image
                    if (importPromiseFragment.getView().findViewById(R.id.not_promises) == null) {
                        feedLayout.addView(promisesNotFound);
                    }
                } else {
                    System.out.println("Another handle way");
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Promise>> call, @NotNull Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
            }
        });
    }
    // ToDo: add offset to args
}
