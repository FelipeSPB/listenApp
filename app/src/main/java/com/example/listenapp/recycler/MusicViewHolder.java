package com.example.listenapp.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listenapp.R;

class MusicViewHolder extends RecyclerView.ViewHolder {
    public TextView artistName;
    public CardView cardViewMusic;
    public ImageView artistImage;

    public MusicViewHolder(View layout) {
        super(layout);
        //artistName = layout.findViewById(R.id.cardViewMusic_textField);
        cardViewMusic = layout.findViewById(R.id.cardViewMusic);
        artistImage = layout.findViewById(R.id.cardViewMusic_imageField);
    }
}

