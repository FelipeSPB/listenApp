package com.example.listenapp.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.*
import base.FragBind
import com.example.listenapp.databinding.FragmentProfileBinding
import com.example.listenapp.main.AboutAppActivity
import com.example.listenapp.main.MainActivity
import com.example.listenapp.model.apimodels.Artist
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.viewmodel.ViewModelProfile
import custom.launchActivity
import custom.viewBind
import custom.viewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.Job
import kotlin.random.Random.Default.nextInt

class ProfileFragmentKT: FragBind<FragmentProfileBinding>() {


    override val binding: FragmentProfileBinding by viewBind()
    private val viewModel by lazy { viewModel<ViewModelProfile>() }

    companion object {
        fun newInstance(bundle: Bundle) = ProfileFragmentKT().apply { arguments = bundle }
    }




    override fun FragmentProfileBinding.onBoundView(){
        viewModel.getTopTrack()
        val tracks = viewModel.tracks
        val listaMusica = ArrayList<Track>()
        tracks.observe(this@ProfileFragmentKT, Observer{ it?.run{
            listaMusica.addAll(it)}})
        val track:String = getTrackSuggested(listaMusica)
        suggested_songs_btn.setOnClickListener{ Toast.makeText(context, listaMusica.toString(), Toast.LENGTH_SHORT).show() }
        setListeners()
    }


    private fun setListeners(){
        val textPatternButton = "A implementar"
        edit_profile_btn.setOnClickListener{ Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show() }
        friends_list_btn.setOnClickListener{ Toast.makeText(context, textPatternButton, Toast.LENGTH_SHORT).show() }
        about_btn.setOnClickListener(View.OnClickListener {
            activity.launchActivity(AboutAppActivity::class.java)
        })

        sign_out_btn.setOnClickListener(View.OnClickListener {
            activity.launchActivity(MainActivity::class.java)})
    }

    fun getTrackSuggested(musica:ArrayList<Track>):String{
        return musica.toString()

    }


}
