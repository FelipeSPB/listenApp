package com.example.listenapp.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
<<<<<<< HEAD
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.listenapp.R;
import com.example.listenapp.recycler.AdapterMusic;
=======
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;


import com.example.listenapp.R;
import com.example.listenapp.model.apimodels.Artist;
import com.example.listenapp.recycler.AdapterMusic;
import com.example.listenapp.viewmodel.ViewModelMusic;


import java.util.ArrayList;

>>>>>>> origin/FeRike

public class MusicFragment extends Fragment {

    Fragment fragment = this;
    ViewModelMusic mainModel;
    View view;
    Context context;
    RecyclerView musicRecycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterMusic adapterMusic;
<<<<<<< HEAD
    //ArrayList<Artists> artists = new ArrayList<>();
    String[] dataSet = {
            "Rafinha",
            "Henrique",
            "Xandão",
            "Daniel",
            "Peter Henry",
            "404",
            "Giulia"};
    private Object AdapterMusic;
    //filler
=======
    ArrayList<Artist> artistSet = new ArrayList<>();

>>>>>>> origin/FeRike



    public static MusicFragmentKT newInstance(Bundle bundle) {
        MusicFragmentKT frag = new MusicFragmentKT();
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
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainModel = ViewModelProviders.of(fragment).get(ViewModelMusic.class);
        findViews();
        recyclerSetup();
        loadAPI();
    }
    private void loadAPI() {
        mainModel.getTracks().observe(fragment, artists -> {
            //artistSet.addAll(artists);
            adapterMusic.notifyDataSetChanged();

        });
        //mainModel.getArtTop();
    }

    private void findViews() {
        musicRecycler = view.findViewById(R.id.recycler_music);
    }

    private void recyclerSetup(){
        layoutManager = new GridLayoutManager(context, 2);
        adapterMusic = new AdapterMusic(artistSet);
        musicRecycler.setLayoutManager(layoutManager);
        musicRecycler.setAdapter((RecyclerView.Adapter) AdapterMusic);
    }

}
