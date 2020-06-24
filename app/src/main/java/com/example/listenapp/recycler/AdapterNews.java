package com.example.listenapp.recycler;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;

public class AdapterNews extends RecyclerView.Adapter<NewsViewHolder> {

    private String[] dataSet;
    Activity activity;

    public AdapterNews(String[] dataSet)
    {
        this.dataSet = dataSet;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        activity =(Activity) viewGroup.getContext();
        View layout = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_viewholder, viewGroup, false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder ViewHolder, int position) {
        final String nome = dataSet[position];
        ViewHolder.headline.setText(nome);
        ViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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
