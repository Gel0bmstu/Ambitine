package com.example.networking.view.feeds.controllers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.debugtools.AmbitinedToast;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Api;
import com.example.networking.model.network.Retrofit.ApiService;
import com.example.networking.model.network.Retrofit.Response.AcceptResponse;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PromiseImportAdapter extends RecyclerView.Adapter<PromiseImportAdapter.PromiseHolder>  implements ItemTouchHelperAdapter{

    private List<Promise> promises;
    private Context context;

    @Override
    public void onItemDismiss(int position, int inAccepted) {
        Promise promise = promises.get(position);
        if (promise.getAccepted() != 0) {
            String promisesResolved = context.getResources().getString(R.string.already_resolved);
            Activity activity = (Activity) context;
            AmbitinedToast.getInstance().debugAboveTheKeyboard(activity, promisesResolved);
            this.notifyDataSetChanged();
        } else {
            this.notifyDataSetChanged();
            promise.setAccepted(inAccepted);
            AcceptResponse newAcceptPromise = new AcceptResponse(promise.getId(), inAccepted);
            ApiService apiService = Api.getApiService();
            Call<ResponseBody> call = apiService.sendAcceptPromise(newAcceptPromise);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.code() == 200) {
                        System.out.println("Promise accepted");
                    } else {
                        System.out.println("Promice declined");
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.println("Send new promise failure");
                    System.out.println(t.toString());
                }
            });
        }
    }

    public static class PromiseHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public MaterialCardView matView;
        public PromiseHolder(MaterialCardView v) {
            super(v);
            matView = v;
        }
    }

    public PromiseImportAdapter(List<Promise> myPromises) {
        promises = myPromises;
    }



    // Create new views (invoked by the layout manager)
    @NotNull
    @Override
    public PromiseImportAdapter.PromiseHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        MaterialCardView v = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promise_import_item, parent, false);
        context = parent.getContext();
        PromiseHolder vh = new PromiseHolder(v);

        return vh;
    }

    public void clear() {
        promises.clear();
    }

    public void addAll(List<Promise> newPromises) {
        clear();
        promises.addAll(newPromises);
        notifyDataSetChanged();
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final PromiseHolder holder, int position) {
        // get promises object
        Promise promise = promises.get(position);
        // Feed username setter
        TextView promiseUsername = holder.matView.findViewById(R.id.promise_username);
        promiseUsername.setText(promise.getAuthor_username());

        // Avatar for user in feed
        ImageView promiseUsernameFeed =  holder.matView.findViewById(R.id.feed_avatar);
        if (!promise.getImg_url().isEmpty()) {
            Picasso.get().load(promise.getAuthor_img_url())
                    .resize(500, 700)
                    .noFade()
                    .into(promiseUsernameFeed);
        }
        // Deposit set
        Float depositValue = promise.getDeposit();
        TextView depositValueTxt = holder.matView.findViewById(R.id.deposit_value);
        depositValueTxt.setText(Float.toString(depositValue));
        // String time
        String pastdueTime = promise.getPastDue();
        TextView timeLeftedTxt = holder.matView.findViewById(R.id.time_lefted);
        timeLeftedTxt.setText(pastdueTime);
        // Promise descriptioon
        String promiseDescription = promise.getPromiseDescription();
        TextView promiseDescriptinView = holder.matView.findViewById(R.id.promise_text);
        promiseDescriptinView.setText(promiseDescription);
        // Accepted handle
        Integer accepted = promise.getAccepted();
        FloatingActionButton acceptedButton = holder.matView.findViewById(R.id.accepted_fab_button);
        FloatingActionButton declinedButton = holder.matView.findViewById(R.id.declined_fab_button);
        if (accepted == -1) {
            acceptedButton.hide();
            declinedButton.hide();
            holder.matView.setCardBackgroundColor(holder.matView.getResources().getColor(R.color.declined_promises));
        } else if (accepted == 1) {
            acceptedButton.hide();
            declinedButton.hide();
            holder.matView.setCardBackgroundColor(holder.matView.getResources().getColor(R.color.ambitine_primary_color));
        } else if (accepted == 0) {
            holder.matView.setCardBackgroundColor(holder.matView.getResources().getColor(R.color.empty_promise));
        }
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return promises.size();
    }


}
