package com.example.listenapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth


class NewAccountViewModelViewModel(application: Application) : AndroidViewModel(application) {

    val firebaseLoginResponse = MutableLiveData<Boolean>()
    private val authRegister: FirebaseAuth = FirebaseAuth.getInstance()
    val user get() = authRegister.currentUser

    fun validarCampo(email: String, password: String, confirmpass: String) {
        if (email.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
            firebaseLoginResponse.postValue(false)
        } else if(email.isNotEmpty() && confirmpass.isNotEmpty()){
            authRegister.createUserWithEmailAndPassword(email, confirmpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseLoginResponse.postValue(true)
                    Log.i("AUTENTICAÇÃO", "Bem-vindo ao App")
                } else {
                    firebaseLoginResponse.postValue(false)
                    Log.i("AUTENTICAÇÃO", "erro ao AUTENTICAR")
                }
            }
        }
    }
}



