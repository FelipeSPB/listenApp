package com.example.listenapp.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.listenapp.R;

class MusicViewHolder extends RecyclerView.ViewHolder {
    public TextView artistName;
    public CardView cardViewMusic;

    public MusicViewHolder(View layout) {
        super(layout);
        artistName = layout.findViewById(R.id.cardViewMusic_textField);
        cardViewMusic = layout.findViewById(R.id.cardViewMusic);
    }
}

