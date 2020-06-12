package com.example.listenapp.recycler;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listenapp.R;

public class PlaylistsViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewContent,textViewQuantity;
    public ImageView imageView;
    public CardView cardView;
    private Activity activity;

    public PlaylistsViewHolder(View layout) {
        super(layout);
        activity = (Activity) layout.getContext();
        textViewContent = layout.findViewById(R.id.cardViewPlay_nameField);
        textViewQuantity = layout.findViewById(R.id.cardViewPlay_qntField);
        imageView = layout.findViewById(R.id.cardViewPlay_imageField);
        cardView = layout.findViewById(R.id.cardViewPlay);
        imageView.setImageDrawable(activity.getDrawable(R.drawable.ic_vinyl_record));
    }
}
