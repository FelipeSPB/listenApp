package com.example.listenapp.main

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.listenapp.R
import com.example.listenapp.viewmodel.SocialMediaLogViewModel
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.facebook.FacebookSdk;

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.FirebaseAuth
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

const val FACEBOOK_INFO = "facebookInfo"

class LoginActivity : AppCompatActivity() {
    var criaConta = false
    lateinit var user: EditText
    lateinit var newUser: EditText
    lateinit var passwordRepeat: EditText
    lateinit var password: EditText
    lateinit var newUserPassword: EditText
    lateinit var buttonLogin: Button
    lateinit var newAcc: Button
    lateinit var signInButtonGoogle: SignInButton
    lateinit var googleAuth: FirebaseAuth


    lateinit var loginContainer: LinearLayout
    lateinit var newAccountContainer: LinearLayout
    lateinit var signInButtonFacebook: LoginButton
    private val loginCode = 300


    private val logViewModel: SocialMediaLogViewModel by viewModels()
    private val callbackManager = CallbackManager.Factory.create()
    private val accessToken: AccessToken? get() = AccessToken.getCurrentAccessToken()
    private val userID get() = accessToken?.userId ?: "4"

    var activity = this

    private val signInClient by lazy {
        GoogleSignIn.getClient(
                this@LoginActivity, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        user = findViewById(R.id.user_login_log)
        newUser = findViewById(R.id.input_user)
        newUserPassword = findViewById(R.id.input_pw)
        passwordRepeat = findViewById(R.id.input_pwrepeat)
        password = findViewById(R.id.pw_login_log)
        buttonLogin = findViewById(R.id.button_login)
        newAcc = findViewById(R.id.toNewAcc_Button)
        signInButtonGoogle = findViewById(R.id.button_google)
        signInButtonFacebook = findViewById(R.id.button_facebook)
        FacebookSdk.sdkInitialize(this)
        loginContainer = findViewById(R.id.login_container)
        newAccountContainer = findViewById(R.id.new_account_container)
        printHashKey()

        logViewModel.login.observe(this, Observer {
            if (it) {
               loginhoSucesso()
            } else {
                Toast.makeText(this, "Deu merda", Toast.LENGTH_LONG).show()
            }
        })
        newAcc.setOnClickListener {
            if (criaConta) {
                logViewModel.validarCampo(newUser.text.toString()
                        , newUserPassword.text.toString()
                        , passwordRepeat.text.toString())
            } else {
                criaConta = true
                newAccountContainer.animateExpand()
                loginContainer.animateExpand(false)
            }
        }

        buttonLogin.setOnClickListener {
            if (criaConta) {
                newAccountContainer.animateExpand(false)
                loginContainer.animateExpand()
                criaConta = false
            } else {

                val passwordText = password.text.toString()
                val userText = user.text.toString()
                if (userText.isEmpty() || passwordText.isEmpty()) {
                    Toast.makeText(activity, "Please! Fill in all the fields.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(activity, "Failed to connect", Toast.LENGTH_LONG).show()
                }
            }
        }
        signInButtonGoogle.setOnClickListener {
            startActivityForResult(signInClient.signInIntent, loginCode)
            signInClient.revokeAccess()
        }
        signInButtonFacebook.setOnClickListener {
            signInButtonFacebook.registerCallback(callbackManager,
                    facebookCallback)
        }
    }

    private fun loginhoSucesso() {
        startActivity(Intent(this, MainScreen::class.java))
    }

    val facebookCallback = object : FacebookCallback<LoginResult> {
        override fun onSuccess(loginResult: LoginResult?) {

           loginhoSucesso()
            toast("SUCESSO!")
        }

        override fun onCancel() {
            toast("Cancellllll!")
        }

        override fun onError(exception: FacebookException) {
            toast(exception.message ?: "errou")
            Log.i("whiskas", exception.message)
        }
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

    fun View.animateExpand(
            expand: Boolean = true,
            duration: Long = 500,
            vertical: Boolean = true
    ) =
            AnimatorSet().run {
                interpolator = AccelerateDecelerateInterpolator()
                play(
                        ValueAnimator.ofInt(
                                if (vertical) height else width,
                                newSizeValue(expand, vertical)
                        ).apply {
                            this.duration = duration
                            addUpdateListener(this, vertical)
                        })
                start()
            }

    private fun View.newSizeValue(expand: Boolean, vertical: Boolean) = if (!expand) 0 else {
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (vertical) measuredHeight else measuredWidth
    }

    fun View.addUpdateListener(valueAnimator: ValueAnimator, vertical: Boolean) =
            valueAnimator.addUpdateListener { animator ->
                (animator.animatedValue as Int).let {
                    if (vertical) layoutParams.height = it else layoutParams.width = it
                }
                requestLayout()
            }
    private fun printHashKey() = try {
        var hashKey = ""

        val info: PackageInfo = packageManager.getPackageInfo(
                packageName, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) PackageManager.GET_SIGNING_CERTIFICATES else PackageManager.GET_SIGNATURES
        )

        val signatures =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) info.signingInfo.apkContentsSigners else info.signatures

        signatures.forEach { signature ->
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            hashKey = String(Base64.encode(md.digest(), 0))
            Log.i(javaClass.name, "printHashKey() Hash Key: $hashKey")
        }
    } catch (e: NoSuchAlgorithmException) {
        Log.e(javaClass.name, "printHashKey()", e)
    } catch (e: Exception) {
        Log.e(javaClass.name, "printHashKey()", e)
    }
}







