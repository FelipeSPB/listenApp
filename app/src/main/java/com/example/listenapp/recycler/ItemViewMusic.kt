package com.example.listenapp.recycler

import android.widget.ImageView
import com.example.listenapp.R
import com.example.listenapp.custom.newPanel
import com.example.listenapp.custom.recyclerAdapter
import com.example.listenapp.databinding.MusicsViewholderBinding
import com.example.listenapp.main.WebViewActivity
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.repository.RepositoryDatabase

import custom.*
import com.example.listenapp.custom.adapter.ItemViewBuilder


class ItemViewMusic : ItemViewBuilder<Track, MusicsViewholderBinding>() {

    override val binding: MusicsViewholderBinding by viewBind()

    override fun MusicsViewholderBinding.onBind(position: Int) {
        
        (collection as ArrayList<Track>).elementAt(position).run {
            cardViewMusicImageField.setImageFromURLwError("http://www.vagalume.com.br/" + artist.formatName() + "/images/profile.jpg",
                    R.drawable.ic_vinyl_record)
            cardViewMusicTextField.text = name
            val list = RepositoryDatabase(context).getAccessPlay().all
            favButton.onClick {

                newPanel(R.layout.playlist){
                    val closeBtn = findViewById<ImageView>(R.id.panel_close)
                    val recycler = findViewById<VerticalRecycler>(R.id.fav_musics)
                    val adapter = recyclerAdapter<ItemViewPlaylists>(list)
                    recycler?.adapter = adapter

                    closeBtn?.onClick { dismiss() }

                }
            }
            cardViewMusic.onClick{
                act.launchActivity(WebViewActivity::class.java){
                    putString("URL", url)
                }
            }
        }
    }

}
