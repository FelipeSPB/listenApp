package com.example.listenapp.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.listenapp.R;
import com.example.listenapp.model.apimodels.Artist;
import com.example.listenapp.model.apimodels.News;
import com.example.listenapp.recycler.AdapterHot;
import com.example.listenapp.recycler.AdapterNews;
import com.example.listenapp.viewmodel.ViewModelNews;


import java.util.ArrayList;


public class NewsFragment extends Fragment{

    View view;
    Context context;
    Fragment fragment = this;
    ViewModelNews mainModel;
    RecyclerView hotNews,recyclerNews;
    RecyclerView.LayoutManager layoutManager, layoutManagerNews;
    AdapterHot adapterHot;
    AdapterNews adapterNews;
    ArrayList<Artist> hotSpot = new ArrayList<>();
    ArrayList<News> news = new ArrayList<>();

    SnapHelper helper;

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
        this.context = context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainModel = ViewModelProviders.of(fragment).get(ViewModelNews.class);
        findViews();
        recyclerSetup();
        loadAPI();

    }

    @SuppressLint("FragmentLiveDataObserve")
    private void loadAPI() {
        mainModel.getNewsSet().observe(fragment, hotspots -> {
            hotSpot.addAll(hotspots);
            adapterHot.notifyDataSetChanged();

        });
        mainModel.getAllNewsSet().observe((LifecycleOwner) context, newsSet -> {
            news.addAll(newsSet);
            adapterNews.notifyDataSetChanged();
        });
        mainModel.news();
        mainModel.hotSpot();
    }

    private void findViews(){
        hotNews = view.findViewById(R.id.recycler_hotnews);
        recyclerNews = view.findViewById(R.id.recycler_news);
    }

    private void recyclerSetup(){
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        adapterHot = new AdapterHot(hotSpot);
        hotNews.setLayoutManager(layoutManager);
        hotNews.setAdapter(adapterHot);
        helper = new PagerSnapHelper();
        helper.attachToRecyclerView(hotNews);
        layoutManagerNews = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterNews = new AdapterNews(news);
        recyclerNews.setLayoutManager(layoutManagerNews);
        recyclerNews.setAdapter(adapterNews);
    }

}
