package com.example.listenapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class MainScreen extends AppCompatActivity {

    FragmentManager manager = getSupportFragmentManager();
    BottomNavigationView botNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        achaViews();
        setListener();
        fragLoad(new NewsFragment());

    }

    private void achaViews() {
        setContentView(R.layout.activity_main_screen);
        botNav = findViewById(R.id.bottom_nav);

    }
    private void setListener(){
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_news:
                        fragLoad(new NewsFragment());
                        break;
                    case R.id.menu_music:
                        fragLoad(new MusicFragment());
                        break;
                    case R.id.menu_playlist:
                        fragLoad(new PlaylistsFragment());
                        break;
                    case R.id.menu_profile:
                        fragLoad(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }
    @NotNull
    private void fragLoad(final Fragment fragment) {
        boolean fragVoltou = fragBack(fragment.getClass().getName());

        if (!fragVoltou) {
            manager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }
    }

    private boolean fragBack(String fragName) {
        return manager.popBackStackImmediate(fragName, 0);
    }


}
