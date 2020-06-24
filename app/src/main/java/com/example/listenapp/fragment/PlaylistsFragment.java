package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.example.listenapp.R;
import com.example.listenapp.model.Playlist;
import com.example.listenapp.recycler.AdapterPlay;

import java.util.ArrayList;

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
        mContext = context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPlaylists(new Playlist());
                adapterPlay.notifyDataSetChanged();
                adapterPlay.updateDataSet();
                playlistIndex++;
            }
        };
    }
    private void recyclerSetup(){
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        adapterPlay = new AdapterPlay(playlists);
        playlistsRecycler.setLayoutManager(layoutManager);
        playlistsRecycler.setAdapter(adapterPlay);


    }
    private void setPlaylists(final Playlist playlist){
        playlist.setPlaylistName(dataSet[playlistIndex]);
        playlist.setQuantity(playlistIndex);
        playlists.add(playlist);
        System.out.println(playlists.toString());

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
