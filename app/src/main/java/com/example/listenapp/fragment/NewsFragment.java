package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    RecyclerView hotNews;
    Context mContext;
    LinearLayoutManager layoutManager;

    public NewsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_news, container, false);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    private void findViews(){
        //hotNews = getView().findViewById(R.id.recycler_hot_news);
    }

    private void configureRecycler(){
        hotNews.setLayoutManager(layoutManager);
        hotNews.setItemAnimator(new DefaultItemAnimator());
    }

    private void createNewsArray(){
        ArrayList<Integer> hotFeed = new ArrayList<>();

    }

}
