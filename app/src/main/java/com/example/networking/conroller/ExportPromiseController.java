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
import com.example.networking.view.ExportPromiseAdapter;
import com.example.networking.view.ExportPromiseFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.networking.model.network.Retrofit.Api.BASE_URL;

public class ExportPromiseController {
    private ExportPromiseFragment exportPromiseFragment;

    public ExportPromiseController(ExportPromiseFragment feedFragment) {
        this.exportPromiseFragment = feedFragment;
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
        Call<List<Promise>> call = service.getAllExportPromises();
        System.out.println("WE INB");
        call.enqueue(new Callback<List<Promise>>() {
            @Override
            public void onResponse(Call<List<Promise>> call, Response<List<Promise>> response) {
                System.out.println("WATA SHAKA LAKA");
                if (response.code() == 200) {
                    RelativeLayout feedLayout = exportPromiseFragment.getView().findViewById(R.id.export_feed_layout);
                    TextView promisesNotFound = exportPromiseFragment.getView().findViewById(R.id.not_promises);
                    if (promisesNotFound != null) {
                        feedLayout.removeView(promisesNotFound);
                    }
                    assert response.body() != null;
                    System.out.println("WATA SHAKA LAKA");
                    // Tmp method to get data
                    RecyclerView recyclerView = exportPromiseFragment.getView().findViewById(R.id.export_promise_feed);


                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(exportPromiseFragment.getActivity()));


                    // exportPromiseController.setFeedData();
//                    exportPromiseController.setFeedData();
                    String[] myStringArray;
                    List<Promise> promises = response.body();
                    ExportPromiseAdapter mAdapter = new ExportPromiseAdapter(promises);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } else if (response.code() == 404) {
                    TextView promisesNotFound = new TextView(exportPromiseFragment.getContext());
                    promisesNotFound.setText("You have no promises so far.");
                    promisesNotFound.setId(R.id.not_promises);
                    // Dont work (
                    promisesNotFound.setGravity(Gravity.CENTER_HORIZONTAL);
                    promisesNotFound.setTextSize(25);
                    promisesNotFound.setTextColor(exportPromiseFragment.getResources().getColor(R.color.ambitine_primary_color));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0,350,0,0);
                    promisesNotFound.setLayoutParams(params);
                    RelativeLayout feedLayout = exportPromiseFragment.getView().findViewById(R.id.export_feed_layout);
                    // ToDo: Maybe add image
                    if (exportPromiseFragment.getView().findViewById(R.id.not_promises) == null) {
                        feedLayout.addView(promisesNotFound);
                    }
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
    // ToDo: add offset to args
}
