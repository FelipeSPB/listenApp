package com.example.listenapp.viewmodel
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SocialMediaLogViewModel : ViewModel(){

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val loginResponse = MutableLiveData<Boolean>()
    val user get() = auth.currentUser
    val firebaseLoginResponse = MutableLiveData<Boolean>()

    fun logIn(data: Intent?) = try {
        GoogleSignIn.getSignedInAccountFromIntent(data).run {
            val credential = GoogleAuthProvider.getCredential(result?.idToken, null)
            auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) onLoginSuccess() else onLoginFail()
                    }
        }
    } catch (exception: Exception) {
        onLoginFail()
    }

    val onLoginSuccess = {
        loginResponse.postValue(true)
    }

    val onLoginFail = {
        loginResponse.postValue(false)
    }

    fun validarCampo(email: String, password: String, confirmpass: String) {
        if (email.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
            firebaseLoginResponse.postValue(false)
        } else if(email.isNotEmpty() && confirmpass.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email, confirmpass).addOnCompleteListener { task ->
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
