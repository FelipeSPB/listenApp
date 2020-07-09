package com.example.listenapp.recycler;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;
import com.example.listenapp.main.MainActivity;
import com.example.listenapp.main.WebViewActivity;
import com.example.listenapp.model.apimodels.News;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<NewsViewHolder> {

    private ArrayList<News> dataSet;
    Activity activity;

    public AdapterNews(ArrayList<News> dataSet)
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

        final News newsItem = dataSet.get(position);
        Picasso.get().load(newsItem.formatImage()).into(ViewHolder.image);
        System.out.println(newsItem.formatImage());
        ViewHolder.headline.setText(newsItem.getHeadline());
        ViewHolder.cardView.setOnClickListener(Click -> {
            activity.startActivity(new Intent(activity, WebViewActivity.class)
                    .putExtra("URL",newsItem.getUrl())
            );
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
