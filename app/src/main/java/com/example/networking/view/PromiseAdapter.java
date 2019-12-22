package com.example.networking.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.model.models.Promise;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PromiseAdapter extends RecyclerView.Adapter<PromiseAdapter.PromiseHolder> {

    private List<Promise> promises;

    public static class PromiseHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public MaterialCardView matView;
        public PromiseHolder(MaterialCardView v) {
            super(v);
            matView = v;
        }
    }

    public PromiseAdapter(List<Promise> myPromises) {
        promises = myPromises;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PromiseAdapter.PromiseHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        MaterialCardView v = (MaterialCardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promise_export_item, parent, false);
//        ...
        PromiseHolder vh = new PromiseHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PromiseHolder holder, int position) {
        // get promises object
        Promise promise = promises.get(position);
        // Feed username setter
        TextView promiseUsername = holder.matView.findViewById(R.id.promise_username);
        promiseUsername.setText(promise.getUsername());

        // Avatar for user in feed
        ImageView promiseUsernameFeed =  holder.matView.findViewById(R.id.feed_avatar);
        if (!promise.getImg_url().isEmpty()) {
            Picasso.get().load(promise.getImg_url())
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
        if (accepted == -1) {
            holder.matView.setCardBackgroundColor(holder.matView.getResources().getColor(R.color.declined_promises));
        } else if (accepted == 1) {
            holder.matView.setCardBackgroundColor(holder.matView.getResources().getColor(R.color.ambitine_primary_color));
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return promises.size();
    }

}
