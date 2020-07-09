package com.example.listenapp.recycler;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
=======

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
>>>>>>> origin/FeRike
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.listenapp.R;
import com.example.listenapp.model.apimodels.Artist;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdapterHot extends RecyclerView.Adapter<HotNewsViewHolder> {
    private ArrayList<Artist> dataSet;
    Activity activity;

    public AdapterHot(ArrayList<Artist> dataSet) {
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
        final Artist hotspot = dataSet.get(position);
        Picasso.get().load("http://www.vagalume.com.br/"+hotspot.formatName()+"/images/profile.jpg").into(ViewHolder.newsImage);
        System.out.println("http://www.vagalume.com.br/"+hotspot.formatName()+"/images/profile.jpg");
        ViewHolder.textViewContent.setText(hotspot.getName());
        ViewHolder.textViewContent.setOnClickListener(Click -> {

        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
