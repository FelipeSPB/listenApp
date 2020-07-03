package com.example.listenapp.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import com.example.listenapp.R
import com.example.listenapp.viewmodel.NewAccountViewModelViewModel
import com.google.firebase.auth.FirebaseAuth

class NewAccountActivity : AppCompatActivity() {
    lateinit var user: EditText
    lateinit var password: EditText
    lateinit var passwordRepeat: EditText
    lateinit var confirm: Button
    lateinit var login: Button
    lateinit var accInfo: Bundle
    lateinit var layout: CoordinatorLayout
    lateinit var loginFirebaseAuth: FirebaseAuth
    lateinit var authStateListener: FirebaseAuth.AuthStateListener
    lateinit var firebaseAuth: FirebaseAuth

    private val viewModel : NewAccountViewModelViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)
        user = findViewById(R.id.input_user)
        password = findViewById(R.id.input_pw)
        passwordRepeat = findViewById(R.id.input_pwrepeat)
        confirm = findViewById(R.id.confirm_Button)
        login = findViewById(R.id.toLogin_Button)
        layout = findViewById(R.id.coordinator)

        viewModel.firebaseLoginResponse.observe(this, Observer {
            if(it){
                startActivity(Intent(this, MainScreen::class.java))
            } else{
                Toast.makeText(this, "Deu merda", Toast.LENGTH_LONG).show()
            }
        })
        firebaseAuth = FirebaseAuth.getInstance()
        confirm = findViewById(R.id.confirm_Button)
        confirm.setOnClickListener(View.OnClickListener {
            viewModel.validarCampo(user.text.toString()
                    ,password.text.toString()
                    ,passwordRepeat.text.toString())
        })
    }
}
