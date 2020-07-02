package com.example.listenapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth


class NewAccountViewModelViewModel(application: Application) : AndroidViewModel(application) {

    val validao = MutableLiveData<Boolean>()
    private val authRegister: FirebaseAuth = FirebaseAuth.getInstance()
    val user get() = authRegister.currentUser

    fun validarCampo(email: String, password: String, confirmpass: String) {
        if (email.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
            validao.postValue(false)
            Log.i("VALIDACAO", "erro ao validar string")
        } else if( password ==confirmpass){
            authRegister.createUserWithEmailAndPassword(email, confirmpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    validao.postValue(true)
                    Log.i("AUTENTICAÇÃO", "Bem-vindo ao App")
                } else {
                    validao.postValue(false)
                    Log.i("AUTENTICAÇÃO", "erro ao AUTENTICAR")
                }
            }
        }
    }
}



