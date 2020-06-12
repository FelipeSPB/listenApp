package com.example.listenapp.recycler;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;
import com.example.listenapp.model.Playlist;

import java.util.ArrayList;

public class AdapterMusic extends RecyclerView.Adapter<MusicViewHolder> {
  //  ArrayList<Playlist> dataSet;
    // ArrayList<Playlist> dataSetFull;
    String[] dataSet;
    Activity activity;

    public AdapterMusic(String[] dataSet) {
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

        final String nome = dataSet[position];
        ViewHolder.artistName.setText(nome);
        //ViewHolder.textViewQuantity.setText(playlist.getQuantity().toString());
        /*if (playlist.getPlaylistImage() == null) {
            ViewHolder.imageView.setImageResource(R.drawable.ic_vinyl_record);
        }
        else{
            ViewHolder.imageView.setImageResource(playlist.getPlaylistImage());
        }*/

        ViewHolder.cardViewMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {

        return dataSet.length;

    }


}

