package com.example.listenapp.recycler


import com.example.listenapp.R
import com.example.listenapp.databinding.FavoritesViewholderBinding
import com.example.listenapp.model.Music
import custom.*
import com.example.listenapp.custom.adapter.ItemViewBuilder

class ItemViewFavorites : ItemViewBuilder<Music, FavoritesViewholderBinding>() {

    override val binding: FavoritesViewholderBinding by viewBind()

    override fun FavoritesViewholderBinding.onBind(position: Int) {
        
        (collection as ArrayList<Music>).elementAt(position).run {
            binding.cardViewFavoriteTextField.text = musicName
            binding.cardViewFavoriteImageField
                   .setImageFromURLwError("http://www.vagalume.com.br/" + image
                           + "/images/profile.jpg", R.drawable.ic_vinyl_record)

            }
        }
}


