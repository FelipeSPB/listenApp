package com.example.listenapp.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.listenapp.R;


public class RecyclerMusic extends AppCompatActivity {
    public RecyclerView recycler = findViewById(R.id.recycler);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);


    }
}
