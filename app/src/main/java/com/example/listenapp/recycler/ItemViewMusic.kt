package com.example.listenapp.recycler




import com.example.listenapp.R
import com.example.listenapp.databinding.MusicsViewholderBinding
import com.example.listenapp.main.WebViewActivity
import com.example.listenapp.model.apimodels.Track
import custom.*
import custom.adapter.ItemViewBuilder

class ItemViewMusic : ItemViewBuilder<Track, MusicsViewholderBinding>() {

    override val binding: MusicsViewholderBinding by viewBind()

    override fun MusicsViewholderBinding.onBind(position: Int) {
        
        (collection as ArrayList<Track>).elementAt(position).run {
            //binding.cardViewMusicImageField.setImageFromURL("http://www.vagalume.com.br/" + artist.formatName() + "/images/profile.jpg")
            binding.cardViewMusicImageField.setImageFromURLwError("http://www.vagalume.com.br/" + artist.formatName() + "/images/profile.jpg",
                    R.drawable.ic_vinyl_record)
            binding.cardViewMusicTextField.text = name
            println(name)
            /*if (name.length > 10){
                binding.cardViewMusicTextField.textSize = 10.toFloat().sp
            }*/
            binding.cardViewMusic.onClick{
                activity.launchActivity(WebViewActivity::class.java){
                    putString("URL", url)
                }
            }
        }
    }

}
