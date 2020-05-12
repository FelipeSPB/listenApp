package com.example.listenapp.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.listenapp.R;


public class RecyclerMusic extends AppCompatActivity {
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public RecyclerView recycler = findViewById(R.id.recyclerMusic);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.);
        recycler = findViewById(R.id.recycler);
        layoutManager = new GridLayoutManager(this,2);
        adapter = new Adapter(chamada);

        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }
}
