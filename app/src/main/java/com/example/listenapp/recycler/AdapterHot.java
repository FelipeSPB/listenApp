package com.example.listenapp.recycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.listenapp.R;
import com.example.listenapp.main.AccPlaceholder;

public class AdapterHot extends RecyclerView.Adapter<HotNewsViewHolder> {
    private String[] dataSet;
    Activity activity;

    public AdapterHot(String[] dataSet) {
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
        final String nome = dataSet[position];
        ViewHolder.textViewContent.setText(nome);
        ViewHolder.textViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle pacote = new Bundle();
                pacote.putString("USER", ViewHolder.textViewContent.getText().toString());
                pacote.putString("PASS", "João2020");
                activity.startActivity(new Intent(activity, AccPlaceholder.class).putExtras(pacote));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
