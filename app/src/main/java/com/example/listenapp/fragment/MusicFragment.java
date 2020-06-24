package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
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
    private Object AdapterMusic;
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
        musicRecycler.setAdapter((RecyclerView.Adapter) AdapterMusic);
    }

}
