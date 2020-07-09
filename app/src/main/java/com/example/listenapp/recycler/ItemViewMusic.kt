package com.example.listenapp.recycler

import android.content.DialogInterface
import android.widget.EditText
import android.widget.ImageView
import com.example.listenapp.R
import com.example.listenapp.custom.adapter.ItemViewBuilder
import com.example.listenapp.custom.newPanel
import com.example.listenapp.custom.recyclerAdapter
import com.example.listenapp.databinding.MusicsViewholderBinding
import com.example.listenapp.main.WebViewActivity
import com.example.listenapp.model.Music
import com.example.listenapp.model.MusicPlaylist
import com.example.listenapp.model.Playlist
import com.example.listenapp.model.apimodels.Track
import com.example.listenapp.repository.RepositoryDatabase
import custom.*


class ItemViewMusic : ItemViewBuilder<Track, MusicsViewholderBinding>() {

    override val binding: MusicsViewholderBinding by viewBind()

    override fun MusicsViewholderBinding.onBind(position: Int) {

        (collection as ArrayList<Track>).elementAt(position).run {
            cardViewMusicImageField.setImageFromURLwError("http://www.vagalume.com.br/" + artist.formatName() + "/images/profile.jpg",
                    R.drawable.ic_vinyl_record)
            cardViewMusicTextField.text = name



            favButton.onClick {
                val list = RepositoryDatabase(context).getAccessPlay().all
                val music = Music(name, "", url)
                val dataset: ArrayList<MusicPlaylist> = ArrayList()
                if (list.size == 0) {
                    val dialog = InputDialog(context, context.getString(R.string.name_playlist), R.layout.playlist_create_music_layout)
                    dialog.positiveListener = DialogInterface.OnClickListener { dialogInterface: DialogInterface?, which: Int ->
                        val namePlay: EditText = dialog.view.findViewById(R.id.playlist_name)
                        val playlist = Playlist()
                        playlist.playlistName = namePlay.text.toString()
                        RepositoryDatabase(context).getAccessPlay().insertAll(playlist)

                    }
                    dialog.show()
                } else {
                    dataset.add(MusicPlaylist(music, list))
                    newPanel(R.layout.playlist) {
                        val closeBtn = findViewById<ImageView>(R.id.panel_close)
                        val recycler = findViewById<VerticalRecycler>(R.id.fav_musics)
                        val adapter = recyclerAdapter<ItemViewPlaylists>(dataset)
                        recycler?.adapter = adapter
                        closeBtn?.onClick { dismiss() }

                    }
                }
            }

            cardViewMusic.onClick {
                act.launchActivity(WebViewActivity::class.java) {
                    putString("URL", url)
                }
            }
        }
    }

}
