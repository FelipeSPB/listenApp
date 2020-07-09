package com.example.listenapp.recycler;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listenapp.R;

public class HotNewsViewHolder<CardView> extends RecyclerView.ViewHolder {
    public TextView textViewContent;
    public ImageView newsImage;
    public CardView cardView;

    public HotNewsViewHolder(View layout) {
        super(layout);
        textViewContent = layout.findViewById(R.id.cardView_textField);
<<<<<<< HEAD
        cardView = (CardView) layout.findViewById(R.id.cardView);
=======
        cardView = layout.findViewById(R.id.cardView);
        newsImage = layout.findViewById(R.id.hotnews_image);
>>>>>>> origin/FeRike
    }
}
