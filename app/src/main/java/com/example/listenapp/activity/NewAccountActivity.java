package com.example.listenapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.listenapp.main.CustomSnackbar;
import com.example.listenapp.R;
import com.example.listenapp.main.MainScreen;


public class NewAccountActivity extends AppCompatActivity {

    EditText user, password, passwordRepeat;
    Button confirm, login;
    Bundle accInfo;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        bundleSetup();
        setClicks();
    }
    private void findViews() {
        setContentView(R.layout.activity_new_account);
        user = findViewById(R.id.input_user);
        password = findViewById(R.id.input_pw);
        passwordRepeat = findViewById(R.id.input_pwrepeat);
        confirm = findViewById(R.id.confirm_Button);
        login = findViewById(R.id.toLogin_Button);
        layout = findViewById(R.id.coordinator);
    }

    private void bundleSetup() {
        accInfo = new Bundle();

    }
    private void setClicks() {
        confirm.setOnClickListener(goTO(MainScreen.class, accInfo));
        login.setOnClickListener(goTO(LoginActivity.class));

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
    private View.OnClickListener goTO(final Class target, final Bundle targetBundle) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().isEmpty() || password.getText().toString().isEmpty() ||
                        passwordRepeat.getText().toString().isEmpty()){
                    new CustomSnackbar().makeSB(layout, getString(R.string.empty_fields_msg),15,15).show();
                }
                else {
                    accInfo.putString("USER", user.getText().toString());
                    accInfo.putString("PASS", password.getText().toString());
                    Activity activity = (Activity) view.getContext();
                    activity.startActivity(new Intent(activity, target).putExtras(targetBundle));
                }
            }
        };
    }
}
