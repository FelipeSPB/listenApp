package com.example.listenapp.recycler;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;

public class AdapterHot extends RecyclerView.Adapter<HotNewsViewHolder> {
    private String[] dataSet;

    public AdapterHot(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public HotNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotnews_viewholder, viewGroup, false);
        return new HotNewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HotNewsViewHolder ViewHolder, int position) {
        final String nome = dataSet[position];
        ViewHolder.textViewContent.setText(nome);
        ViewHolder.textViewContent.setOnClickListener(new View.OnClickListener() {
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
