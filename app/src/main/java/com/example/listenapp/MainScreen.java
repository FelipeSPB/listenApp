package com.example.listenapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.listenapp.custom.CustomDialog;
import com.example.listenapp.fragment.MusicFragment;
import com.example.listenapp.fragment.NewsFragment;
import com.example.listenapp.fragment.PlaylistsFragment;
import com.example.listenapp.fragment.ProfileFragment;

import org.jetbrains.annotations.NotNull;

public class MainScreen extends AppCompatActivity {

    final Fragment fNews = NewsFragment.newInstance(new Bundle());
    final Fragment fMusic = MusicFragment.newInstance(new Bundle());
    //final Fragment fPlaylist = new UploadFragment();
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
        //fragLoad(new NewsFragment(), "news");

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
                        //fragLoad(fNews);
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
            new CustomDialog(context, getString(R.string.act_end_title), getString(R.string.act_end_msg))
                    .show(manager, getString(R.string.act_end_tag));
        }
        else{
          fragLoad(fNews);
          botNav.setSelectedItemId(R.id.menu_news);
        }
    }
}


