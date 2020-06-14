package com.example.listenapp.recycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.listenapp.R;
import com.example.listenapp.AccPlaceholder;
import com.example.listenapp.model.apimodels.Hotspot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterHot extends RecyclerView.Adapter<HotNewsViewHolder> {
    private ArrayList<Hotspot> dataSet;
    Activity activity;

    public AdapterHot(ArrayList<Hotspot> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public HotNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        activity =(Activity) viewGroup.getContext();
        View layout = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotnews_viewholder, viewGroup, false);
        return new HotNewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final HotNewsViewHolder ViewHolder, int position) {
        final Hotspot hotspot = dataSet.get(position);
        Picasso.get().load(hotspot.getPicSrc()).into(ViewHolder.newsImage);
        ViewHolder.textViewContent.setText(hotspot.getDescr());
        ViewHolder.textViewContent.setOnClickListener(Click -> {

        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
