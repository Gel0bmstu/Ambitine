package com.example.networking.controller;

import android.graphics.Canvas;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.networking.R;
import com.example.networking.controller.swiper.PromiseSwipeController;
import com.example.networking.controller.swiper.PromiseSwipeControllerActions;
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
    private PromiseSwipeController promiseSwipeController;

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
                    RelativeLayout feedLayout = Objects.requireNonNull(exportPromiseFragment.getView()).findViewById(R.id.export_feed_layout);
                    TextView promisesNotFound = exportPromiseFragment.getView().findViewById(R.id.not_promises);
                    if (promisesNotFound != null) {
                        feedLayout.removeView(promisesNotFound);
                    }
                    assert response.body() != null;


                    // Tmp method to get data
                    RecyclerView recyclerView = exportPromiseFragment.getView().findViewById(R.id.export_promise_feed);
                    recyclerView.setHasFixedSize(true);



                    recyclerView.setLayoutManager(new LinearLayoutManager(exportPromiseFragment.getActivity()));
                    List<Promise> promises = response.body();
                    ExportPromiseAdapter mAdapter = new ExportPromiseAdapter(promises);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } else if (response.code() == 404) {
                    TextView promisesNotFound = new TextView(exportPromiseFragment.getContext());
                    promisesNotFound.setText(R.string.no_promises);
                    promisesNotFound.setId(R.id.not_promises);
                    // Dont work (
                    promisesNotFound.setGravity(Gravity.CENTER_HORIZONTAL);
                    promisesNotFound.setTextSize(25);
                    promisesNotFound.setTextColor(exportPromiseFragment.getResources().getColor(R.color.ambitine_primary_color));
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0,350,0,0);
                    promisesNotFound.setLayoutParams(params);
                    RelativeLayout feedLayout = Objects.requireNonNull(exportPromiseFragment.getView()).findViewById(R.id.export_feed_layout);
                    // ToDo: Maybe add image
                    if (exportPromiseFragment.getView().findViewById(R.id.not_promises) == null) {
                        feedLayout.addView(promisesNotFound);
                    }
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
                    // Add swiper

                    List<Promise> promises = response.body();
                    PromiseImportAdapter mAdapter = new PromiseImportAdapter(promises);
                    // 4. set adapter
                    recyclerView.setAdapter(mAdapter);
                    // 5. set item animator to DefaultAnimator
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    // Add swiper
                    promiseSwipeController = new PromiseSwipeController(new PromiseSwipeControllerActions() {
                        @Override
                        public void onRightClicked(int position) {
                            System.out.println("RIGHT CLICKED");
                        }
                        @Override
                        public void onLeftClicked(int position) {
                            System.out.println("LEFT CLICKED");
                        }
                    });

                    ItemTouchHelper itemTouchhelper = new ItemTouchHelper(promiseSwipeController);
                    itemTouchhelper.attachToRecyclerView(recyclerView);
                    recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                            promiseSwipeController.onDraw(c);
                        }
                    });

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
