package com.example.listenapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.listenapp.R
import com.example.listenapp.main.MainScreen
import com.example.listenapp.viewmodel.SocialMediaLogViewModel
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var buttonLogin: Button
    lateinit var newAcc: Button
    lateinit var signInButtonGoogle: SignInButton
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var signInButtonFacebook: Button
    lateinit var loginFirebaseAuth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var auth: FirebaseAuth
    private val loginCode = 300
    private val callbackManager = CallbackManager.Factory.create()
    private val logViewModel: SocialMediaLogViewModel by viewModels()
    var activity = this


    private val loginIntent by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build())
                .signInIntent
        auth = FirebaseAuth.getInstance()
        signInButtonGoogle.setOnClickListener {
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, loginCode)

    }

    override fun onActivityResult(
            requestCode: Int,
            resultCode: Int,
            data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == loginCode) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if (task.isSuccessful) {
                try {
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    Log.w("SignInActivity", "Google sign in failed", e)
                }
            } else {
                Log.w("SignInActivity", exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val addOnCompleteListener = auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                    }
                    Log.d("SignInActivity", "signInWithCredential:success")
                    val intent = Intent(this, MainScreen::class.java)
                    startActivity(intent)
                    finish()
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user = findViewById(R.id.user_login_log)
        password = findViewById(R.id.pw_login_log)
        buttonLogin = findViewById(R.id.button_login)
        newAcc = findViewById(R.id.toNewAcc_Button)
        signInButtonGoogle = findViewById(R.id.button_google)
        signInButtonFacebook = findViewById(R.id.button_facebook)




        newAcc.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, NewAccountActivity::class.java)
            startActivity(intent)
        })

        buttonLogin.setOnClickListener(View.OnClickListener {
            val userNameText = user.getText().toString()
            val passwordText = password.getText().toString()
            if (userNameText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(activity, "Please, gill in all the fields!", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)
            }
        })


        logViewModel.loginResponse.observe(this, androidx.lifecycle.Observer {
            if (it) {
                val intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(activity, "Failed, please try again", Toast.LENGTH_LONG).show()
            }
        })
        signInButtonGoogle.setOnClickListener {
            startActivityForResult(intent, loginCode)

        }
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            when (requestCode) {
                loginCode -> logViewModel.logIn(data)
            }
        }
        fun LoginButton.chamaLogin() {
          //  registerCallback(callbackManager, facebookCallback)
        }

            }

    }





