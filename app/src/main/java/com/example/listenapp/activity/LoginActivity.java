package com.example.listenapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.listenapp.custom.CustomSnackbar;

import com.example.listenapp.main.MainScreen;
import com.example.listenapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;






public class LoginActivity extends AppCompatActivity {

    private SignInButton signInButton;
    private GoogleSignInClient mGoogleSinginClient;
    private String TAG ="MainActivity";
    private FirebaseAuth mAuth;
    private int RC_SIGN_IN =1;



    FirebaseUser user;
    EditText password;
    Button confirm, newAcc;
    Bundle accInfo;
    CoordinatorLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        bundleSetup();
        setClicks();
        signInButton = findViewById(R.id.button_google);
        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSinginClient = GoogleSignIn.getClient(this,gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
    }

    private void signIn() {
        Intent signInIntent = mGoogleSinginClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult (task);
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        GoogleSignInAccount acc = null;
        try {
            acc = completedTask.getResult(ApiException.class);
            Toast.makeText(LoginActivity.this, "Signed in successfully", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        } catch (ApiException e) {
            Toast.makeText(LoginActivity.this, "Signed in failed :( ", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }

    }

    private void FirebaseGoogleAuth (GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Ae",Toast.LENGTH_SHORT).show();
                    uptadeUI (user);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Mal Ae",Toast.LENGTH_SHORT).show();
                    uptadeUI (null);
                }
            }
        });
    }
     private void uptadeUI (FirebaseUser firebaseUser){
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account != null){
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName= account.getFamilyName();
            String personEmail= account.getEmail();
            String personId= account.getId();
            Uri personPhoto = account.getPhotoUrl();

            Toast.makeText(LoginActivity.this,personName +personEmail ,Toast.LENGTH_SHORT).show();




     }



     }
    private void findViews() {
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.user_login_log);
        password = findViewById(R.id.pw_login_log);
        confirm = findViewById(R.id.confirm_log_Button);
        newAcc = findViewById(R.id.toNewAcc_Button);
        layout = findViewById(R.id.coordinator_login);
    }

    private void bundleSetup() {
        accInfo = new Bundle();

    }
    private void setClicks() {
        confirm.setOnClickListener(goTO(MainScreen.class, accInfo));
        newAcc.setOnClickListener(goTO(NewAccountActivity.class));

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
                if(user.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
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
