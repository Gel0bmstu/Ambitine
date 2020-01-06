package com.example.networking.controller.swiper;


import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.networking.view.feeds.controllers.ItemTouchHelperAdapter;

import static androidx.recyclerview.widget.ItemTouchHelper.LEFT;
import static androidx.recyclerview.widget.ItemTouchHelper.RIGHT;



public class PromiseSwipeController extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;



    public PromiseSwipeController(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, LEFT | RIGHT);
    }



    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if(direction == LEFT) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition(), 1);
        } else if(direction == RIGHT) {
            mAdapter.onItemDismiss(viewHolder.getAdapterPosition(), -1);
        }
    }


}


