package com.example.listenapp.activity
/*
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.listenapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

class LoginSocialMedia  :AppCompatActivity(){
    private var googleSignInButton: SignInButton? = null
    private var googleSignInClient: GoogleSignInClient? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        googleSignInButton = findViewById(R.id.button_google)
        loginWithGoogle()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                101 -> try {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                    val conta = task.getResult(ApiException::class.java)
                    SucessLogin(conta)
                } catch (e: ApiException) {
                    Log.i("LOG", "Error: " + e.message)
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
}*/