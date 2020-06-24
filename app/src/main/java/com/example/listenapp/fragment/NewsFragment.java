package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnFlingListener;
import androidx.recyclerview.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;
import com.example.listenapp.recycler.AdapterHot;
import com.example.listenapp.recycler.AdapterNews;

import java.util.ArrayList;

public class NewsFragment extends Fragment{

    RecyclerView hotNews,recyclerNews;
    RecyclerView.LayoutManager layoutManager, layoutManagerNews;
    AdapterHot adapterHot;
    AdapterNews adapterNews;
    View view;
    Context mContext;
    String[] dataSet = {
            "Rafinha",
            "Henrique",
            "Xandão",
            "Daniel",
            "Peter Henry",
            "404",
            "Giulia",
            "Lucas",
            "Eduardo",
            "Sandro",
            "Felipe",
            "Gabriel"};
    SnapHelper helper;
    private Object AdapterNews;
    private Object AdapterHot;
    //filler


    public NewsFragment() {
    }

    public static NewsFragment newInstance(Bundle bundle) {
        NewsFragment frag = new NewsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = container;
        return inflater.inflate(R.layout.fragment_news, container, false);


    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        recyclerSetup();
    }

    private void findViews(){
        hotNews = view.findViewById(R.id.recycler_hotnews);
        recyclerNews = view.findViewById(R.id.recycler_news);
    }

    private void recyclerSetup(){
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        adapterHot = new AdapterHot(dataSet);
        hotNews.setLayoutManager(layoutManager);
        hotNews.setAdapter((RecyclerView.Adapter) AdapterHot);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(hotNews);
        layoutManagerNews = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        adapterNews = new AdapterNews(dataSet);
        recyclerNews.setLayoutManager(layoutManagerNews);
        recyclerNews.setAdapter((RecyclerView.Adapter) AdapterNews);
    }

    private void createNewsArray(){
        ArrayList<Integer> hotFeed = new ArrayList<>();

    }


}
