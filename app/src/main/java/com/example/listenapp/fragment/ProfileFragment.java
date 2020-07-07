package com.example.listenapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.listenapp.R;
import com.example.listenapp.custom.DesinflateKt;
import com.example.listenapp.custom.ExpanderKt;
import com.example.listenapp.main.MainActivity;

public class ProfileFragment extends Fragment {

    Context context;
    View view;
    ImageView userPicture;
    TextView userName, quantityPlaylists;
    Button suggestedSongs, editProfile, friendsList, aboutApp, signOut;
    LinearLayout dropdownAbout;
    Boolean buttonAboutAppWasClicked = false;


    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = container;
        return inflater.inflate(R.layout.fragment_profile, container, false);

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        setListeners();
    }


    private void findViews(){
        userPicture = view.findViewById(R.id.user_picture);
        userName = view.findViewById(R.id.user_name);
        quantityPlaylists = view.findViewById(R.id.quantity_playlists);
        suggestedSongs = view.findViewById(R.id.suggested_songs_btn);
        editProfile = view.findViewById(R.id.edit_profile_btn);
        friendsList = view.findViewById(R.id.friends_list_btn);
        aboutApp = view.findViewById(R.id.about_btn);
        signOut = view.findViewById(R.id.sign_out_btn);
        dropdownAbout = view.findViewById(R.id.dropdown_about_listen_app);
    }

    private void setListeners(){
        String textPatternButton = "A implementar";
        suggestedSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show();
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show();
            }
        });
        friendsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show();
            }
        });
        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonAboutAppWasClicked == true && aboutApp.isPressed()){
                    ExpanderKt.animateExpandAbout(dropdownAbout, false, 500, false);
                    buttonAboutAppWasClicked = false;
                }else{
                    ExpanderKt.animateExpandAbout(dropdownAbout, true, 500, true);
                    buttonAboutAppWasClicked = true;
                }
            }});

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) view.getContext();
                activity.startActivity(new Intent(activity, MainActivity.class));
            }
        });
    }




}
