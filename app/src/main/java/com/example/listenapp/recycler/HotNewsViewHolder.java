package com.example.listenapp.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.listenapp.R;

public class HotNewsViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewContent;
    public CardView cardView;

    public HotNewsViewHolder(View layout) {
        super(layout);
        textViewContent = layout.findViewById(R.id.cardView_textField);
        cardView = layout.findViewById(R.id.cardView);
    }
}
