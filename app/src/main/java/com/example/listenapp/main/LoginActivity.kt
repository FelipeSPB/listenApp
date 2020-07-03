package com.example.listenapp.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.listenapp.R
import com.example.listenapp.viewmodel.SocialMediaLogViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth

const val FACEBOOK_INFO = "facebookInfo"

class LoginActivity : AppCompatActivity() {

    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var buttonLogin: Button
    lateinit var newAcc: Button
    lateinit var signInButtonGoogle: SignInButton
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var signInButtonFacebook: LoginButton
    private val loginCode = 300
    lateinit var firebaseAuth: FirebaseAuth


    private val logViewModel: SocialMediaLogViewModel by viewModels()
    private val callbackManager = CallbackManager.Factory.create()
    private val accessToken: AccessToken? get() = AccessToken.getCurrentAccessToken()
    private val userID get() = accessToken?.userId ?: "4"

    var activity = this


    private val loginGoogle by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        ).signInIntent
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
        firebaseAuth = FirebaseAuth.getInstance()



        newAcc.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, NewAccountActivity::class.java)
            startActivity(intent)
        })
        buttonLogin.setOnClickListener(View.OnClickListener {
            val passwordText = password.getText().toString()
            val userText = user.getText().toString()
            if (userText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(activity, "Please! Fill in all the fields.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity, "Failed to connect", Toast.LENGTH_LONG).show()
            }
        })
        signInButtonGoogle.setOnClickListener(View.OnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, loginCode)
            //  logViewModel.logOff()
            googleSignInClient.revokeAccess()
        })
        signInButtonFacebook.setOnClickListener {
            signInButtonFacebook.registerCallback(callbackManager,
                    facebookCallback as FacebookCallback <LoginResult>)
        }
    }


    val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult?) {

            startActivity(Intent(this@LoginActivity, MainScreen::class.java))
            toast("SUCESSO!")
        }

        override fun onCancel() {
            toast("Cancellllll!")
        }

        override fun onError(exception: FacebookException) {
            toast("Errouuuuuuuu!")
        }
    }

    private fun facebookLoginCall() {
        LoginManager.getInstance().registerCallback(callbackManager,
                facebookCallback as FacebookCallback<LoginResult>)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            loginCode -> logViewModel.logIn(data)
        }
    }
    private var toast: Toast? = null

    fun Context.toast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(this, message, LENGTH_SHORT)
        toast?.show()

    }

}







