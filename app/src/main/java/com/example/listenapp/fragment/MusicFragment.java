package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;


import com.example.listenapp.R;
import com.example.listenapp.model.Playlist;
import com.example.listenapp.recycler.AdapterMusic;
import com.example.listenapp.recycler.AdapterPlay;

import java.util.ArrayList;


public class MusicFragment extends Fragment {

    View view;
    Context mContext;
    RecyclerView musicRecycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterMusic adapterMusic;
    //ArrayList<Artists> artists = new ArrayList<>();
    String[] dataSet = {
            "Rafinha",
            "Henrique",
            "Xandão",
            "Daniel",
            "Peter Henry",
            "404",
            "Giulia"};
    //filler



    public static MusicFragment newInstance(Bundle bundle) {
        MusicFragment frag = new MusicFragment();
        frag.setArguments(bundle);
        return frag;
    }

    public MusicFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
            view = container;
         return inflater.inflate(R.layout.fragment_music, container, false);

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

    private void findViews() {
        musicRecycler = view.findViewById(R.id.recycler_music);
    }

    private void recyclerSetup(){
        layoutManager = new GridLayoutManager(mContext, 2);
        adapterMusic = new AdapterMusic(dataSet);
        musicRecycler.setLayoutManager(layoutManager);
        musicRecycler.setAdapter(adapterMusic);
    }

}
