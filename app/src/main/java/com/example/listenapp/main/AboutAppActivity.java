package com.example.listenapp.main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.listenapp.R;



public class AboutAppActivity extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setListeners();
    }
    private void findViews() {
        setContentView(R.layout.activity_about_app);
        backButton = findViewById(R.id.back_profile_button);
    }

    private View.OnClickListener goTO(final Class umaClasse) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) view.getContext();
                activity.startActivity(new Intent(activity, umaClasse).putExtras(new Bundle()));
            }
        };
    }

    private void setListeners(){
        backButton.setOnClickListener(goTO(MainScreen.class));
    }


}