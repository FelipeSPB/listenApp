package com.example.listenapp.fragment;


import android.arch.lifecycle.ViewModelProviders;
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
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.listenapp.R;
import com.example.listenapp.data.DatabaseBuilder;
import com.example.listenapp.data.dao.AccessPlay;
import com.example.listenapp.model.Playlist;
import com.example.listenapp.recycler.AdapterPlay;
import com.example.listenapp.viewmodel.ViewModelPlaylist;

import java.util.ArrayList;

import custom.InputDialog;

public class PlaylistsFragment extends Fragment {

    ViewModelPlaylist mainModel;
    View view;
    Context context;
    Fragment fragment = this;
    Button addPlaylist;
    SearchView searchBar;
    RecyclerView playlistsRecycler;
    RecyclerView.LayoutManager layoutManager;
    AdapterPlay adapterPlay;
    private int playlistIndex = 0;
    ArrayList<Playlist> playlists = new ArrayList<>();
    AccessPlay accessPlay;

    String[] dataSet = {
            "Rafinha",
            "Henrique",
            "Xandão",
            "Daniel",
            "Peter Henry",
            "404",
            "Giulia"};


    public PlaylistsFragment() {
    }

    public static PlaylistsFragment newInstance(Bundle bundle) {
       PlaylistsFragment frag = new PlaylistsFragment();
        frag.setArguments(bundle);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = container;
        return inflater.inflate(R.layout.fragment_playlists, container, false);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainModel = ViewModelProviders.of(fragment).get(ViewModelPlaylist.class);
        accessPlay = DatabaseBuilder.getAppDatabase(context).accessPlay();
        playlists.clear();
        playlists.addAll(accessPlay.getAll());
        findViews();
        setListeners();
        recyclerSetup();

    }

    private void findViews(){
        playlistsRecycler = view.findViewById(R.id.recycler_playlists);
        addPlaylist = view.findViewById(R.id.add_playlist_button);
        searchBar = view.findViewById(R.id.playlist_search);
        searchBar.setImeOptions(EditorInfo.IME_ACTION_DONE);
    }

    private void setListeners() {
        addPlaylist.setOnClickListener(createPlaylist());
        searchBar.setIconifiedByDefault(false);
        searchBar.setOnQueryTextListener(searchInput());
    }

    private View.OnClickListener createPlaylist() {
        return view -> {
            final InputDialog dialog = new InputDialog(context, getString(R.string.new_playlist), R.layout.playlist_create_layout);
            dialog.setPositiveListener((dialogInterface, which) -> {
                EditText namePlay = dialog.getView().findViewById(R.id.playlist_name);
                setPlaylists(namePlay.getText().toString());
            });
            dialog.show();
        };
    }
    private void recyclerSetup(){
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterPlay = new AdapterPlay(playlists);
        playlistsRecycler.setLayoutManager(layoutManager);
        playlistsRecycler.setAdapter(adapterPlay);
    }

    public void setPlaylists(String name){
        Playlist playlist = new Playlist();
        playlist.setPlaylistName(name);
        playlists.add(playlist);
        accessPlay.insertAll(playlist);
        adapterPlay.notifyDataSetChanged();
        adapterPlay.updateDataSet();


    }

    private SearchView.OnQueryTextListener searchInput() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String input) {
                adapterPlay.getFilter().filter(input);
                return false;
            }
        };
    }


}
