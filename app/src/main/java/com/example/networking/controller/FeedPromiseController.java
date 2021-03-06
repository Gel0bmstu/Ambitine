package com.example.networking.controller;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.networking.R;
import com.example.networking.controller.swiper.PromiseSwipeController;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.view.feeds.controllers.ExportPromiseAdapter;
import com.example.networking.view.feeds.fragments.ExportPromiseFragment;
import com.example.networking.view.feeds.fragments.ImportPromiseFragment;
import com.example.networking.view.feeds.controllers.PromiseImportAdapter;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedPromiseController {
    private ExportPromiseFragment exportPromiseFragment;
    private ImportPromiseFragment importPromiseFragment;

    // Import promise
    private RecyclerView importRecyclerView = null;
    private PromiseImportAdapter mAdapter = null;

    // Export promise
    private ExportPromiseAdapter mExpAdapter = null;
    private RecyclerView exportRecyclerView = null;



    public FeedPromiseController(ExportPromiseFragment feedFragment) {
        this.importPromiseFragment = null;
        this.exportPromiseFragment = feedFragment;
    }
    public FeedPromiseController(ImportPromiseFragment feedFragment) {
        this.exportPromiseFragment = null;
        this.importPromiseFragment = feedFragment;
    }


    public void setExportFeedData() {
        ApiService service = Api.getApiService();
        Call<List<Promise>> call = service.getAllExportPromises();
        System.out.println("WE INB");
        call.enqueue(new Callback<List<Promise>>() {
            @Override
            public void onResponse(@NotNull Call<List<Promise>> call, @NotNull Response<List<Promise>> response) {
                if (response.code() == 200) {
                    LinearLayout noExportPromises = Objects.requireNonNull(exportPromiseFragment.getView()).findViewById(R.id.no_export_promises_layout);
                    noExportPromises.setVisibility(View.GONE);

                    RelativeLayout feedLayout = Objects.requireNonNull(exportPromiseFragment.getView()).findViewById(R.id.export_feed_layout);
                    TextView promisesNotFound = exportPromiseFragment.getView().findViewById(R.id.not_promises);
                    if (promisesNotFound != null) {
                        feedLayout.removeView(promisesNotFound);
                    }
                    assert response.body() != null;


                    List<Promise> promises = response.body();
                    if (mAdapter == null) {
                        exportRecyclerView = exportPromiseFragment.getView().findViewById(R.id.export_promise_feed);
                        exportRecyclerView.setHasFixedSize(true);
                        exportRecyclerView.setLayoutManager(new LinearLayoutManager(exportPromiseFragment.getActivity()));
                        mExpAdapter = new ExportPromiseAdapter(promises);
                        exportRecyclerView.setAdapter(mExpAdapter);
                        exportRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    } else {
                        System.out.println("Not null");
                        mExpAdapter.addAll(promises);
                    }

                } else if (response.code() == 404) {
                    LinearLayout noExportPromises = Objects.requireNonNull(exportPromiseFragment.getView()).findViewById(R.id.no_export_promises_layout);
                    noExportPromises.setVisibility(View.VISIBLE);
                } else {
                    System.out.println("Another handle way");
                }
                exportPromiseFragment.setRefreshingStatus(false);
            }

            @Override
            public void onFailure(@NotNull Call<List<Promise>> call, @NotNull Throwable t) {
                System.out.println("FOCK");
                System.out.println(t.toString());
                exportPromiseFragment.setRefreshingStatus(false);
            }
        });
    }


    public void setImportFeedData() {
        ApiService service = Api.getApiService();
        Call<List<Promise>> call = service.getAllImportPromises();
        call.enqueue(new Callback<List<Promise>>() {
            @Override
            public void onResponse(@NotNull Call<List<Promise>> call, @NotNull Response<List<Promise>> response) {
                if (response.code() == 200) {
                    LinearLayout noImportPromises = Objects.requireNonNull(importPromiseFragment.getView()).findViewById(R.id.no_import_promises_layout);
                    noImportPromises.setVisibility(View.GONE);
                    assert response.body() != null;
                    RelativeLayout feedLayout = Objects.requireNonNull(importPromiseFragment.getView()).findViewById(R.id.import_promise_layout);
                    TextView promisesNotFound = importPromiseFragment.getView().findViewById(R.id.not_promises);
                    if (promisesNotFound != null) {
                        feedLayout.removeView(promisesNotFound);
                    }
                    // Tmp method to get data


                    List<Promise> promises = response.body();
                    if (mAdapter == null) {
                        importRecyclerView = importPromiseFragment.getView().findViewById(R.id.import_promise_feed);
                        importRecyclerView.setHasFixedSize(true);
                        importRecyclerView.setLayoutManager(new LinearLayoutManager(importPromiseFragment.getActivity()));
                        mAdapter = new PromiseImportAdapter(promises);
                        importRecyclerView.setAdapter(mAdapter);
                        importRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        // Add swipe callback
                        ItemTouchHelper.Callback callback = new PromiseSwipeController(mAdapter);
                        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
                        mItemTouchHelper.attachToRecyclerView(importRecyclerView);
                    } else {
                        mAdapter.addAll(promises);
                    }


                } else if (response.code() == 404) {
                    LinearLayout noImportPromises = Objects.requireNonNull(importPromiseFragment.getView()).findViewById(R.id.no_import_promises_layout);
                    noImportPromises.setVisibility(View.VISIBLE);
                } else {
                    System.out.println("Another handle way");
                }
                importPromiseFragment.setRefreshingStatus(false);
            }

            @Override
            public void onFailure(@NotNull Call<List<Promise>> call, @NotNull Throwable t) {
                importPromiseFragment.setRefreshingStatus(false);
            }
        });
    }
    // ToDo: add offset to args
}
