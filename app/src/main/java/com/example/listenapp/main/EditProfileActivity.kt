package com.example.listenapp.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.listenapp.R
import kotlinx.android.synthetic.main.edit_profile_activity.*

class EditProfileActivity : Activity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)
        setListeners()
    }

    fun setListeners(){
        val textPatternButton = "A implementar"
        change_Button.setOnClickListener{Toast.makeText(applicationContext, textPatternButton, Toast.LENGTH_SHORT).show() }
        confirm_Button.setOnClickListener{Toast.makeText(applicationContext, textPatternButton, Toast.LENGTH_SHORT).show() }
    }

}
