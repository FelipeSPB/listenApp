package com.example.listenapp.main;


import android.content.Context;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


import com.example.listenapp.R;
import com.example.listenapp.custom.ConfirmationDialog;
import com.example.listenapp.fragment.MusicFragmentKT;
import com.example.listenapp.fragment.NewsFragment;
import com.example.listenapp.fragment.PlaylistsFragment;
import com.example.listenapp.fragment.ProfileFragment;

public class MainScreen extends AppCompatActivity {

    final Fragment fNews = NewsFragment.newInstance(new Bundle());
    final Fragment fMusic = MusicFragmentKT.Companion.newInstance(new Bundle());
    final Fragment fPlay = PlaylistsFragment.newInstance(new Bundle());
    //final Fragment fProfile = new SettingFragment();
    FragmentManager manager = getSupportFragmentManager();
    BottomNavigationView botNav;
    CoordinatorLayout layout;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        achaViews();
        setListener();
        fragLoad(fNews);


    }

    private void achaViews() {
        setContentView(R.layout.activity_main_screen);
        botNav = findViewById(R.id.bottom_nav);
        layout = findViewById(R.id.ms_msgbox);


    }

    private void setListener(){
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_news:
                        fragLoad(fNews);
                        break;
                    case R.id.menu_music:
                        fragLoad(fMusic);
                        break;
                    case R.id.menu_playlist:
                        fragLoad(fPlay);
                        break;
                    case R.id.menu_profile:
                        fragLoad(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void fragLoad(final Fragment fragment) {
        boolean fragVoltou = fragBack(fragment.getClass().getName());
        if (!fragVoltou) {
            manager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }
    }

    private boolean fragBack(String fragName) {
        return manager.popBackStackImmediate(fragName, 0);
    }

    @Override
    public void onBackPressed() {
        Fragment selectedFragment = null;
        for (Fragment fragment : manager.getFragments()) {
            if(fragment.isVisible()){
                selectedFragment = fragment;
                break;
            }
            return;
        }
        if (selectedFragment instanceof NewsFragment) {
            new ConfirmationDialog(context, getString(R.string.act_end_title), getString(R.string.act_end_msg),
                    getString(R.string.Answer_yes), getString(R.string.Answer_no)).show();

        }
        else{
          fragLoad(fNews);
          botNav.setSelectedItemId(R.id.menu_news);
        }
    }
}


