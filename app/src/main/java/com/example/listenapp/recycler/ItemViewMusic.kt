package com.example.listenapp.recycler


import com.example.listenapp.databinding.MusicsViewholderBinding
import com.example.listenapp.main.WebViewActivity
import com.example.listenapp.model.apimodels.Artist
import custom.adapter.ItemViewBuilder
import custom.launchActivity
import custom.onClick
import custom.setImageFromURL
import custom.viewBind

class ItemViewMusic : ItemViewBuilder<Artist, MusicsViewholderBinding>() {

    override val binding: MusicsViewholderBinding by viewBind()

    override fun MusicsViewholderBinding.onBind(position: Int) {
        
        (collection as ArrayList<Artist>).elementAt(position).run {
            binding.cardViewMusicImageField.setImageFromURL("http://www.vagalume.com.br/" + formatName() + "/images/profile.jpg")
            //binding.cardViewMusicTextField.text = name
            binding.cardViewMusic.onClick{
                activity.launchActivity(WebViewActivity::class.java){
                    putString("URL", url)
                }
            }
        }
    }

}
