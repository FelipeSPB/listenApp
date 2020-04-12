package com.example.listenapp;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewAccountActivity extends AppCompatActivity {

    EditText user, password, passwordRepeat;
    Button confirm;
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
        layout = findViewById(R.id.coordinator);
    }
    private void bundleSetup() {
        accInfo = new Bundle();

        //putString("chave", "Não, aí eu coloco a variavel");

    }
    private void setClicks() {
        confirm.setOnClickListener(goTO(AccPlaceholder.class, accInfo));

    }
    private View.OnClickListener goTO(final Class target, final Bundle targetBundle) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(user.getText().toString().isEmpty() || password.getText().toString().isEmpty() || passwordRepeat.getText().toString().isEmpty()){
                    new CustomSnackbar().makeSB(layout, getString(R.string.empty_fields_msg),15,15).show();
                }
                else {

                }
                //Activity activity = (Activity) view.getContext();
                //activity.startActivity(new Intent(activity, target).putExtras(targetBundle));
            }
        };
    }
}
