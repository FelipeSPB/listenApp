package com.example.listenapp.recycler

import com.example.listenapp.databinding.PlaylistsAddViewholderBinding
import com.example.listenapp.model.Playlist
import custom.*
import com.example.listenapp.custom.adapter.ItemViewBuilder

class ItemViewPlaylists : ItemViewBuilder<Playlist, PlaylistsAddViewholderBinding>() {

    override val binding: PlaylistsAddViewholderBinding by viewBind()

    override fun PlaylistsAddViewholderBinding.onBind(position: Int) {
        
        (collection as List<Playlist>).elementAt(position).run {
                cardViewPlayAddNameField.text = playlistName
                cardViewPlayAddQntField.text = quantity.toString()
                cardViewPlayAddImageField.setImageResource(playlistImage)
            }
        }
}


