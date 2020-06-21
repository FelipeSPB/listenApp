package com.example.listenapp.recycler;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listenapp.R;

class NewsViewHolder extends RecyclerView.ViewHolder {
     TextView headline;
     ImageView image;
     CardView cardView;
     Activity activity;


    public NewsViewHolder(View layout) {
        super(layout);
        activity = (Activity) layout.getContext();
        headline = layout.findViewById(R.id.cardViewNews_headline);
        image = layout.findViewById(R.id.cardViewNews_imageField);
        cardView = layout.findViewById(R.id.cardViewNews);
        image.setImageDrawable(activity.getDrawable(R.drawable.learn_music_theory));
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}
