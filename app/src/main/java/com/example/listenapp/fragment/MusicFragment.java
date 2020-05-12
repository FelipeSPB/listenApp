package com.example.listenapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.GridLayoutManager;


import com.example.listenapp.R;

import java.util.ArrayList;


public class MusicFragment extends Fragment {


<<<<<<< HEAD
=======
    public static MusicFragment newInstance(Bundle bundle) {
        MusicFragment frag = new MusicFragment();
        frag.setArguments(bundle);
        return frag;
    }

>>>>>>> 57110d862f918e67cd4f3c4f8189bc730a80c757
    public MusicFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_music, container, false);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

}
