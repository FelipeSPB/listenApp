package com.example.listenapp.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.listenapp.databinding.PlaylistsAddViewholderBinding
import com.example.listenapp.model.Playlist
import custom.*
import com.example.listenapp.custom.adapter.ItemViewBuilder
import com.example.listenapp.model.MusicPlaylist

class ItemViewPlaylists : ItemViewBuilder<MusicPlaylist, PlaylistsAddViewholderBinding>() {

    override val binding: PlaylistsAddViewholderBinding by viewBind()

    override fun PlaylistsAddViewholderBinding.onBind(position: Int) {
        collection[0].playlists.elementAt(position).run {
            cardViewPlayAddNameField.text = playlistName
            cardViewPlayAddQntField.text = quantity.toString()
            cardViewPlayAddImageField.setImageResource(playlistImage)
            cardViewPlayAdd.onClick { }
        }
    }
}




