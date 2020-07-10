package com.example.listenapp.recycler;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.listenapp.R;
import com.example.listenapp.model.Playlist;
import com.example.listenapp.model.apimodels.Artist;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;


import androidx.recyclerview.widget.RecyclerView;

import com.example.listenapp.R;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<MusicViewHolder> {
    ArrayList<Artist> dataSet;
    Activity activity;

    public AdapterMusic(ArrayList<Artist> dataSet) {
        this.dataSet = dataSet;
       // this.dataSetFull = new ArrayList<Artists>(dataSet);
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        activity =(Activity) viewGroup.getContext();
        View layout = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.musics_viewholder, viewGroup, false);
        return new MusicViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder ViewHolder, int position) {
        final Artist artist = dataSet.get(position);
        Picasso.get().load("http://www.vagalume.com.br/"+artist.formatName()+"/images/profile.jpg").into(ViewHolder.artistImage);
        System.out.println("http://www.vagalume.com.br/"+artist.formatName()+"/images/profile.jpg");
        ViewHolder.artistName.setText(artist.getName());
        ViewHolder.cardViewMusic.setOnClickListener(Click -> {

        });
    }

    @Override
    public int getItemCount() {

        return dataSet.size();

    }


}

