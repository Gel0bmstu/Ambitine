package com.example.networking.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.networking.R;
import com.example.networking.model.models.Promise;
import com.example.networking.model.network.Retrofit.Response.FeedPromiseResponse;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
                .inflate(R.layout.promise_item, parent, false);
//        ...
        PromiseHolder vh = new PromiseHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PromiseHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.matView.setRadius(1);
        // Feed username setter
        TextView promiseUsername = holder.matView.findViewById(R.id.promise_username);
        promiseUsername.setText(promises.get(position).getUsername());

        // Avatar for user in feed
        ImageView promiseUsernameFeed =  holder.matView.findViewById(R.id.feed_avatar);
        Picasso.get().load(promises.get(position).getImg_url())
                .resize(500, 700)
                .noFade()
                .into(promiseUsernameFeed);
        // Deposit set
        Integer depositValue = promises.get(position).getDeposit();


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return promises.size();
    }

}
