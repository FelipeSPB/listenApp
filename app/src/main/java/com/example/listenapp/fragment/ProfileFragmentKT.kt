package com.example.listenapp.fragment


import android.os.Bundle
import android.widget.*
import base.FragBind
import com.example.listenapp.databinding.FragmentProfileBinding
import com.example.listenapp.main.AboutAppActivity
import com.example.listenapp.main.LoginActivity
import com.example.listenapp.viewmodel.ViewModelProfile
import custom.launchActivity

import custom.viewBind
import custom.viewModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragmentKT: FragBind<FragmentProfileBinding>() {


    override val binding: FragmentProfileBinding by viewBind()
    private val viewModel by lazy { viewModel<ViewModelProfile>() }

    companion object {
        fun newInstance(bundle: Bundle) = ProfileFragmentKT().apply { arguments = bundle }
    }

    override fun FragmentProfileBinding.onBoundView(){
      setListeners()
    }

    private fun setListeners(){
        val textPatternButton = "A implementar"
        suggested_songs_btn.setOnClickListener{ Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show() }
        edit_profile_btn.setOnClickListener{ Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show() }
        friends_list_btn.setOnClickListener{ Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show() }
        about_btn.setOnClickListener {
            activity?.launchActivity(AboutAppActivity::class.java)
        }

        sign_out_btn.setOnClickListener {
            activity?.launchActivity(LoginActivity::class.java)
        }
    }

}
