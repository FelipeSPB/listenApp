package com.example.listenapp.viewmodel
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SocialMediaLogViewModel : ViewModel(){
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val loginResponse = MutableLiveData<Boolean>()

    val exitresponse = MutableLiveData<Boolean>()

    val user get() = auth.currentUser


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
    fun logOff() {
        if (user != null) {
            auth.signOut()
            exitresponse.postValue(true)
        } else {
            exitresponse.postValue(false)
        }
    }
}
