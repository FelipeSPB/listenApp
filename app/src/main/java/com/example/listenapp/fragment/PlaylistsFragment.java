package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listenapp.R;

public class PlaylistsFragment extends Fragment {


    View view;
    Context mContext;
    Button addPlaylist;
    SearchView searchBar;
    RecyclerView playlistsRecycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterPlay adapterPlay;
    private int playlistIndex = 0;
    ArrayList<Playlist> playlists = new ArrayList<>();
    String[] dataSet = {
            "Rafinha",
            "Henrique",
            "Xandão",
            "Daniel",
            "Peter Henry",
            "404",
            "Giulia"};
    //filler


    public PlaylistsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_playlists, container, false);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
