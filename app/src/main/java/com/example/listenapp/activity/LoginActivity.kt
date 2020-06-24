package com.example.listenapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.example.listenapp.R
import com.example.listenapp.custom.CustomSnackbar
import com.example.listenapp.main.MainScreen
//import com.example.listenapp.model.ViewModelLogin
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*
import kotlin.math.sign

class LoginActivity : AppCompatActivity() {
    private val callbackManager = CallbackManager.Factory.create()
    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var confirm: Button
    lateinit var newAcc: Button
    lateinit var signInButtonGoogle: SignInButton
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var signInButtonFacebook: Button
    lateinit var auth: FirebaseAuth
    var activity = this

    private val loginCode = 300
  //  private val viewModel: ViewModelLogin by viewModels()



    private val loginIntent by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        ).signInIntent
      auth = FirebaseAuth.getInstance()
        signInButtonGoogle.setOnClickListener {
            signInGoogle()
        }
    }
     private fun signInGoogle(){
         val signInIntent = googleSignInClient.signInIntent
         startActivityForResult(signInIntent,loginCode)

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
        confirm = findViewById(R.id.confirm_log_Button)
        newAcc = findViewById(R.id.toNewAcc_Button)
        signInButtonGoogle = findViewById(R.id.button_google)
        signInButtonFacebook = findViewById(R.id.button_facebook)

        newAcc.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, NewAccountActivity::class.java)
            startActivity(intent)
        })

        confirm.setOnClickListener(View.OnClickListener {
            val userNameText = user.getText().toString()
            val passwordText = password.getText().toString()
            if (userNameText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(activity, "Please, gill in all the fields!", Toast.LENGTH_LONG).show()
            } else {
                val intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)
            }
        })


     /* viewModel.loginResponse.observe(this, androidx.lifecycle.Observer {
            if (it) {
                val intent = Intent(applicationContext, MainScreen::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(activity, "Failed, please try again", Toast.LENGTH_LONG).show()
            }
        })*/
        signInButtonGoogle.setOnClickListener(View.OnClickListener {
            startActivityForResult(intent,loginCode)

        })
    }
}



