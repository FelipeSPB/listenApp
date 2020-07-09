package com.example.listenapp.fragment;



import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.EditText;
import android.widget.SearchView;
import com.example.listenapp.R;
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
    ArrayList<Playlist> playlists = new ArrayList<>();

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
                mainModel.setPlaylists(namePlay.getText().toString());
                updatePlaylists();

            });
            dialog.show();
        };
    }
    private void recyclerSetup(){
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapterPlay = new AdapterPlay(playlists);
        playlistsRecycler.setLayoutManager(layoutManager);
        playlistsRecycler.setAdapter(adapterPlay);
        mainModel.getDataSet().observe(fragment, set -> {
            assert set != null;
            playlists.addAll(set);
            adapterPlay.notifyDataSetChanged();
            adapterPlay.updateDataSet();
        });
        mainModel.getPlaylists();



    }

    public void updatePlaylists(){

        mainModel.getDataSet().observe(fragment, set -> {

            playlists.clear();
            assert set != null;
            playlists.addAll(set);
        });
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
