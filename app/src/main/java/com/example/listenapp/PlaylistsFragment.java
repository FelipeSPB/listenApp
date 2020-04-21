package com.example.listenapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlaylistsFragment extends Fragment {

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
