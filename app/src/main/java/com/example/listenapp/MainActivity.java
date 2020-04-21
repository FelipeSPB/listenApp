package com.example.listenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button newAcc, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        setListeners();
    }
    private void findViews() {
        setContentView(R.layout.activity_main);
        newAcc = findViewById(R.id.novaConta);
        login = findViewById(R.id.login_Button);

    }

    private void setListeners() {
        newAcc.setOnClickListener(goTo(NewAccountActivity.class, new Bundle()));
        login.setOnClickListener(goTo(LoginActivity.class, new Bundle()));

    }

    private View.OnClickListener goTo(final Class umaClasse, final Bundle pacote) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = (Activity) view.getContext();
                activity.startActivity(new Intent(activity, umaClasse).putExtras(pacote));
            }
        };
    }

}